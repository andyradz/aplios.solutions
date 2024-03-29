package com.codigo.aplios.sdk.core.loaders;

public class EncryptDecryptProperty {
	// private final Path propertyFilePath;
	// private final String propertyKey;
	// private final String isPropertyKeyEncrypted;

	// final String decryptedUserPassword;

	// /**
	// * The constructor does most of the work. It initializes all final variables
	// and invoke two
	// methods
	// * for encryption and decryption job. After successful job the constructor
	// puts the decrypted
	// * password in variable to be retrieved by calling class.
	// *
	// *
	// * @param propertyFileName
	// * /Name of the properties file that contains the password
	// * @param userPasswordKey
	// * /Left hand side of the password property as key.
	// * @param isPasswordEncryptedKey
	// * /Key in the properties file that will tell us if the password is already
	// encrypted or not
	// *
	// * @throws Exception
	// */
	// public EncryptDecryptProperty(final Path propertyFilePath, final String
	// userPasswordKey,
	// final String isPasswordEncryptedKey) throws Exception {
	//
	// this.propertyFilePath = propertyFilePath;
	// this.propertyKey = userPasswordKey;
	// this.isPropertyKeyEncrypted = isPasswordEncryptedKey;
	// try {
	// encryptPropertyValue();
	// }
	// catch (final Exception e) {
	// throw new Exception(
	// "Problem encountered during encryption process", e);
	// }
	// this.decryptedUserPassword = decryptPropertyValue();
	//
	// }
	//
	// /**
	// * The method that encrypt password in the properties file. This method will
	// first check if the
	// * password is already encrypted or not. If not then only it will encrypt the
	// password.
	// *
	// * @throws ConfigurationException
	// */
	// private void encryptPropertyValue() {
	//
	// System.out.println("Starting encryption operation");
	// System.out.println("Start reading properties file");
	//
	// // Apache Commons Configuration
	// final Properties config = new Properties();
	//
	// // Retrieve boolean properties value to see if password is already encrypted
	// or not
	// final String isEncrypted = config.getProperty(this.isPropertyKeyEncrypted);
	//
	// // Check if password is encrypted?
	// if (isEncrypted.equals("false")) {
	// final String tmpPwd = config.getProperty(this.propertyKey);
	// // System.out.println(tmpPwd);
	// // Encrypt
	// final StandardPBEStringEncryptor encryptor = new
	// StandardPBEStringEncryptor();
	// // This is a required password for Jasypt. You will have to use the same
	// password to
	// // retrieve decrypted password later. T
	// // This password is not the password we are trying to encrypt taken from
	// properties file.
	// encryptor.setPassword("jasypt");
	// final String encryptedPassword = encryptor.encrypt(tmpPwd);
	// System.out.println("Encryption done and encrypted password is : " +
	// encryptedPassword);
	//
	// // Overwrite password with encrypted password in the properties file using
	// Apache Commons
	// // Cinfiguration
	// // library
	// config.setProperty(this.propertyKey, encryptedPassword);
	// // Set the boolean flag to true to indicate future encryption operation that
	// password is already
	// // encrypted
	// config.setProperty(this.isPropertyKeyEncrypted, "true");
	// // Save the properties file
	// // config.store;
	// }
	// else
	// System.out.println("User password is already encrypted.\n ");
	// }
	//
	// private String decryptPropertyValue() {
	//
	// System.out.println("Starting decryption");
	// final Properties config = new Properties();
	// final String encryptedPropertyValue = config.getProperty(this.propertyKey);
	// // System.out.println(encryptedPropertyValue);
	//
	// final StandardPBEStringEncryptor encryptor = new
	// StandardPBEStringEncryptor();
	// encryptor.setPassword("jasypt");
	// final String decryptedPropertyValue =
	// encryptor.decrypt(encryptedPropertyValue);
	// // System.out.println(decryptedPropertyValue);
	//
	// return decryptedPropertyValue;
	// }
}
