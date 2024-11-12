package uniflee.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;
	@Value("${cloud.aws.region.static}")
	private String region;
	@Value("${cloud.aws.s3.endpoint}")
	private String endpoint;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Bean
	public AmazonS3 amazonS3() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		return AmazonS3ClientBuilder.standard()
			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
			.withCredentials(new AWSStaticCredentialsProvider(credentials))
			.withPathStyleAccessEnabled(true)
			.disableChunkedEncoding()
			.build();
	}
}
