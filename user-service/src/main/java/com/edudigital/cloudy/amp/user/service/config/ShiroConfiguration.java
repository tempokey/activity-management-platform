package com.edudigital.cloudy.amp.user.service.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edudigital.cloudy.amp.user.service.entity.shiro.MShiroFilterFactoryBean;
import com.edudigital.cloudy.amp.user.service.entity.shiro.MyShiroRealm;
import com.edudigital.cloudy.amp.user.service.filter.ShiroUserFilter;

@Configuration
public class ShiroConfiguration {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${amp.user.shiro.cache.xml}")
	private String EHCACHE_FILE_PATH;// = "classpath:config/ehcache-shiro.xml";

	/**
	 * 加载shiroFilter权限控制规则（从数据库读取然后配置）
	 *
	 * @author SHANHY
	 * @create 2016年1月14日
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		/////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		// anon：它对应的过滤器里面是空的,什么都没做
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		filterChainDefinitionMap.put("/web/user/signup", "anon");
		filterChainDefinitionMap.put("/web/user/login", "authc");
		filterChainDefinitionMap.put("/web/user/logout", "user");
		filterChainDefinitionMap.put("/web/user/**", "user");
		filterChainDefinitionMap.put("/mob/user/**", "anon");
		filterChainDefinitionMap.put("/**", "anon");// anon 可以理解为不拦截

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}

	/**
	 * ShiroFilter<br/>
	 * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
	 * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
	 *
	 * @param myShiroRealm
	 * @param stuService
	 * @param scoreDao
	 * @return
	 * @author SHANHY
	 * @create 2016年1月14日
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/web/user/login");
		// 登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/foyer");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error403");

		Map<String, Filter> filters = new HashMap<>();
		filters.put("user", new ShiroUserFilter());
		shiroFilterFactoryBean.setFilters(filters);

		loadShiroFilterChain(shiroFilterFactoryBean);
		return shiroFilterFactoryBean;
	}

	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile(this.EHCACHE_FILE_PATH);
		return em;
	}

	// @Bean(name = "lifecycleBeanPostProcessor")
	// public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
	// return new LifecycleBeanPostProcessor();
	// }

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager);
		return realm;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	// ---------------------shiro end
}
