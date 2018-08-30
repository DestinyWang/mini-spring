# 1. 目标

![](http://oetw0yrii.bkt.clouddn.com/18-8-28/15987484.jpg)

- BeanFactory: 给定一个配置文件, 能够获取 bean 的定义
- BeanDefinition: 定义每个 bean 的基本信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
    <!--xmlns="http://www.springframework.org/schema/beans"-->
    <!--xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"-->
    <!--xsi:schemaLocation="http://www.springframework.org/schema/beans-->
    <!--http://www.springframework.org/schema/beans/spring-beans.xsd"-->

    <bean id="petStore" class="org.destiny.service.v1.PetStoreService"/>
    <bean id="invalidBean" class="org.destiny.service.v1.XxxService"/>
</beans>
```

## 1.1 设计 BeanFactory 的接口
### 1.1.1 BeanDefinition
> 定义每个 bean 的基本信息以及获取信息的接口

```java
public interface BeanDefinition {

    /**
     * 获取 bean 名称
     * @return
     */
    String getBeanClassName();

}
```

### 1.1.2 GenericBeanDefinition

```class
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    /**
     * 获取 bean 名称
     *
     * @return
     */
    @Override
    public String getBeanClassName() {
        return beanClassName;
    }
}
```

### 1.1.3 BeanFactory

> 定义

```java
public interface BeanFactory {
    
    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
```

### 1.1.4 DefaultBeanFactory
> BeanFactory 的默认实现

```java
public class DefaultBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    
    public DefaultBeanFactory(String configPath) {
        loadBeanDefinition(configPath);
    }
    
    /**
     * 获取 bean 实例
     *
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        if (!StringUtils.isNotEmpty(beanId)) {
            throw new NullPointerException("beanId can't be null");
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition doesn't exist");
        }
        // 获取类全路径名
        ClassLoader defaultClassLoader = ClassUtil.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        // 反射创建
        try {
            Class<?> clazz = defaultClassLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("error creating bean with name: " + beanClassName);
        }
    }

    private void loadBeanDefinition(String configFile) {
        InputStream inputStream = null;
        try {
            /*
             * Class.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。
             * 例如有一个 MyTest 类在包 org.destiny 下, 那么 MyTest.class.getResourceAsStream("path")
             * 会在 org.destiny 包下查找相应的资源。
             * 而如果这个 path 是以 '/' 开头的，那么就会从 classpath 的根路径下开始查找。
             * -----------------------------------------------------------------------------------------
             * ClassLoader.getResourceAsStream() 无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。
             * -----------------------------------------------------------------------------------------
             * MyTest.class.getClassLoader().getResourceAsStream("/name")
             * 和 ClassLoader.getClassLoader().getResourceAsStream("name") 的效果是一样的。
             */
            ClassLoader defaultClassLoader = ClassUtil.getDefaultClassLoader();
            inputStream = defaultClassLoader.getResourceAsStream(configPath);
            
            SAXReader reader = new SAXReader();
            Document document = null;
            try {
                document = reader.read(inputStream);
            } catch (DocumentException e) {
                e.printStackTrace();
                throw new BeanCreationException("cannot read xml");
            }
    
            // <beans>
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                // <bean>
                Element element = iterator.next();
                // id=""
                String id = element.attributeValue(ID_ATTRIBUTE);
                // class=""
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
                beanDefinitionRegistry.registryBeanDefinition(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("error parsing xml with path: " + configPath);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

# 2. 将 DefaultBeanFactory 多余职责进行拆分
目前的 `DefaultBeanFactory` 职责很多
- 读取 XML
- 解析 XML
- 注册 Bean
- 获取 Bean

这违背了的单一职责原则