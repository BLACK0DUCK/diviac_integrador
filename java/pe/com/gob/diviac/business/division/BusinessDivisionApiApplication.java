package pe.com.gob.diviac.business.division;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "pe.com.gob.diviac.business.division.adapter.output.http.common.client")
public class BusinessDivisionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessDivisionApiApplication.class, args);
	}

}
