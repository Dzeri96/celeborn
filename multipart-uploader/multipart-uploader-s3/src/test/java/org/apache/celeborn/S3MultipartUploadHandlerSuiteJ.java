package org.apache.celeborn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.s3a.AWSCredentialProviderList;
import org.apache.hadoop.fs.s3a.Constants;
import org.junit.Assert;
import org.junit.Test;

public class S3MultipartUploadHandlerSuiteJ {

  @Test
  public void testGetCredentialsProviderShouldGiveDefaultProvidersOnEmptyConfig() throws Exception {
    Configuration conf = new Configuration();
    AWSCredentialProviderList providers =
        S3MultipartUploadHandler.getCredentialsProvider(null, conf);
    Assert.assertTrue(providers.size() != 0);
    System.out.println(providers.listProviderNames());
  }

  @Test
  public void testGetCredentialsProviderShouldReturnWebIdentityTokenCredentialsProvider()
      throws Exception {
    Configuration conf = new Configuration();
    conf.set(
        Constants.AWS_CREDENTIALS_PROVIDER,
        "com.amazonaws.auth.WebIdentityTokenCredentialsProvider");
    AWSCredentialProviderList providers =
        S3MultipartUploadHandler.getCredentialsProvider(null, conf);
    Assert.assertEquals("WebIdentityTokenCredentialsProvider ", providers.listProviderNames());
  }
}
