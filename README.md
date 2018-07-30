# workerRunner
定时任务随时执行插件

1.通常作为测试人员在测试定时任务时，一般有如下步骤：
  * 修改配置文件中任务的corn表达式；
  * 重启应用，等待任务执行，查看日志；
  * 再次测试时，需重复如上步骤。
  ## 缺点：
  * 操作繁琐
  * 每次需重启等待，耗时
  * 影响测试时间
  ***
 SO，做了这么一个插件。如有问题，欢迎提出交流。
 
 ## 使用步骤：
首先将该工程，导出成jar包：workerRunner.jar

测试使用：
### Springboot工程接入步骤：
* 引入jar包：workerRunner.jar，通常为lib文件夹下
* 启动类添加注解：
 <pre><code>
 @Import(ApplicationContextProvider.class)
 </code></pre>
* 编写配置类：
<pre><code>
	@Configuration
	public class myServletConfig  extends ServletConfig {

	}
 </code></pre>
* 启动应用，浏览器访问ip:port//work/beanName/methodName,worker调用执行成功

### SpringMvc工程接入步骤(web版本需3.0及以上)：
* 引入jar包:workerRunner.jar，通常为lib文件夹下
* spring-config.xml中添加注解扫描：
 ```xml
    <context:component-scan base-package="com.chen.worker.test.servlet"/>
 ```
* 启动应用，浏览器访问ip:port//work/beanName/methodName,worker调用执行成功
### SpringMvc工程接入步骤(web版本需3.0以下)：
* 在web.xml中添加servlet配置：
```xml
  <servlet>
    <servlet-name>workServlet</servlet-name>
    <servlet-class>com.chen.woeker.test.workServlet</servlet-class>
  </servlet>

  <!-- spring mvc处理的请求 -->
  <servlet-mapping>
    <servlet-name>workServlet</servlet-name>
    <url-pattern>/work/*</url-pattern>
  </servlet-mapping>
```
