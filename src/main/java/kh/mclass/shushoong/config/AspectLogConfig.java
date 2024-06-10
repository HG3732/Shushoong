package kh.mclass.shushoong.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AspectLogConfig {

	@Pointcut("execution(public * kh.mclass..*Repository.*(..))")
	public void daoPointcut() {}
	
	@Pointcut("execution(public * kh.mclass..*Service.*(..))")
	public void servicePointcut() {}
	
	@Pointcut("execution(public * kh.mclass..*Controller.*(..))")
	public void controllerPointcut() {}

	@Around("daoPointcut()")
	public Object aroundDaoLog(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		// pjp.getThis() : 클래스명
		// pjp.getSignature().getName(): 타겟메소드명
		log.debug("▶▶▶["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		// pjp.getArgs() : 메소드의 파라메터 값들이 들어있음.
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			log.debug("▽▽▽-args["+i+"] "+args[i]+"");
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// pjp.proceed() : 타겟메소드 호출함
		returnObj = pjp.proceed();
		stopwatch.stop();
		log.debug("▷▷▷[Dao ▷ "+stopwatch.getTotalTimeMillis()+"ms]"+returnObj);
		return returnObj;
	}
	@Around("servicePointcut()")
	public Object aroundServiceLog(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		// pjp.getThis() : 클래스명
		// pjp.getSignature().getName(): 타겟메소드명
		log.debug("▶▶["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		// pjp.getArgs() : 메소드의 파라메터 값들이 들어있음.
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			log.debug("▽▽-args["+i+"] "+args[i]+"");
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// pjp.proceed() : 타겟메소드 호출함
		returnObj = pjp.proceed();
		stopwatch.stop();
		log.debug("▷▷[Svc ▷ "+stopwatch.getTotalTimeMillis()+"ms]"+returnObj);
		return returnObj;
	}
	
	@Around("controllerPointcut()")
	public Object aroundControllerLog(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		// pjp.getThis() : 클래스명
		// pjp.getSignature().getName(): 타겟메소드명
		log.debug("▶["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		// pjp.getArgs() : 메소드의 파라메터 값들이 들어있음.
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			log.debug("▽-args["+i+"] "+args[i]+"");
		}
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// pjp.proceed() : 타겟메소드 호출함
		returnObj = pjp.proceed();
		stopwatch.stop();
		
//		log.info("▷[Ctrl ▷ "+stopwatch.getTotalTimeMillis()+"ms]"+ returnObj);

		//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Gson gson = new GsonBuilder().create();
		log.debug("▷[Ctrl ▷ "+stopwatch.getTotalTimeMillis()+"ms]"+ gson.toJson(returnObj));
		return returnObj;
	}
	
	
	
}
