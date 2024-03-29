package com.codigo.aplios.repository.core;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;

public class EclipseLinkSessionCustomizer implements SessionCustomizer {

	@Override
	public void customize(Session session) throws Exception {

//
//		// Enable concurrent processing of result sets and concurrent loading of load groups.
//		((AbstractSession) session).setIsConcurrent(true);
//
//		// Add sequence generators.
//		Lists.newArrayList(new UUIDSequence("uuid-seq"), new PSRNSequence("psrn-seq"), new FlakeSequence("flake-seq"))
//				.forEach(sequence -> {
//					session.getLogin()
//							.addSequence(sequence);
//				});
//
//		// Convert class names to table names by adding '_' characters when case changes.
//		for (ClassDescriptor descriptor : session.getDescriptors()
//				.values()) {
//			// Only change the table name for non-embeddable entities with no @Table already
//			if (!descriptor.getTables()
//					.isEmpty() && descriptor.getAlias()
//							.equalsIgnoreCase(descriptor.getTableName())) {
//				String tableName = convertCaseToUnderscores(descriptor.getTableName());
//				descriptor.setTableName(tableName);
//				for (IndexDefinition index : descriptor.getTables()
//						.get(0)
//						.getIndexes()) {
//					index.setTargetTable(tableName);
//				}
//			}
//		}
	}

	private static String convertCaseToUnderscores(String name) {

		StringBuffer buf = new StringBuffer(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}

}
