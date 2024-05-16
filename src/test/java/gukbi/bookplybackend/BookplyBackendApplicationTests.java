package gukbi.bookplybackend;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookplyBackendApplicationTests {

	@Test
	void contextLoads() {
		stringEncryptor();
	}

	public void stringEncryptor() {
		System.out.println(jasyptEncoding("92UzcR4lfngRsjUZu6PORQTLvys7qsuFWU2vYPZBZGU+OL9wzy8IYKKYcCMxS9FH"));
		System.out.println(jasyptEncoding("/JCzYoX1INM/SY99a26cXb7BaQKMf8Mz57Mmr0EW1/wtL8mY9KrksHH9Z2A0W4rCVF0+lzw3UlpX9N8jgR9Sbg=="));
		System.out.println(jasyptEncoding("F+4VhBdLjcDofFcxUKE5FNe7XK0kyymeNwplUNj0DpbshxCcuLnmKgvgc7DaUoiu/cJpbbJC5naDvlKeS8bGjTAjXSlDJnX//iAcWeXpNGqwZxD96ERl/3cdzrN603pLhtYGOETnVBOO0BAu9FHz/A=="));
		System.out.println(jasyptEncoding("FOpmMhy8IQiCu57gqx9P14bsIFCn5nvyK8BYLL+3ctgUx7sXXnNQXmxlrqsw5m8bXJSUm7/79PlDEJO0fI0nG+NsZhos89dquaUfpm0ap8Q="));
	}

	public String jasyptEncoding(String value) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(""); // 사용할 기본 비밀번호 설정
    config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); // 사용할 암호화 알고리즘 설정
    config.setPoolSize("1"); // 인스턴스 풀 사이즈 설정
    config.setStringOutputType("base64"); // 인코딩 방식 설정
    config.setKeyObtentionIterations("1000");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
    encryptor.setConfig(config);

		return encryptor.decrypt(value);
	}
}
