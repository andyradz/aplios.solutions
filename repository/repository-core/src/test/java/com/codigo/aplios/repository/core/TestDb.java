package com.codigo.aplios.repository.core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import com.codigo.aplios.domain.model.Parameter;
import com.codigo.aplios.domain.model.calendar.Calendar;
import com.codigo.aplios.domain.model.calendar.CalendarDay;
import com.codigo.aplios.domain.model.calendar.CalendarPrimaryKey;
import com.codigo.aplios.domain.model.locale.Country;

/*import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.domain.XmlCountries;
import com.codigo.aplios.domain.model.domain.XmlCountry;
import com.codigo.aplios.domain.model.locale.Country;*/

//*********************************************************************************************
//Zamienić klucz kalendarza na dzien tj 2019-01-01 a nie trzy pola
//*********************************************************************************************

public class TestDb {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bitshop");
	
	private static void employeeUpdate() {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		
		Parameter paramManager = new Parameter();
		paramManager.setParameterKey("PCAL");
		paramManager.setSubtypeKey("01");
		
		Parameter paramManager1 = new Parameter();
		paramManager1.setParameterKey("PCAL");
		paramManager1.setSubtypeKey("10");
		
		Parameter param1 = new Parameter();
		param1.setParameterKey("INTR");
		param1.setSubtypeKey("01");
		param1.setSubtype(paramManager);
		
		Parameter param2 = new Parameter();
		param2.setParameterKey("INTR");
		param2.setSubtypeKey("02");
		param2.setSubtype(paramManager);
		
		Parameter param3 = new Parameter();
		param3.setParameterKey("DVCA");
		param3.setSubtypeKey("01");
		param3.setSubtype(paramManager);
		
		Parameter param4 = new Parameter();
		param4.setParameterKey("DVCA");
		param4.setSubtypeKey("02");
		param4.setSubtype(paramManager);
		
		Parameter param5 = new Parameter();
		param5.setParameterKey("REDM");
		param5.setSubtypeKey("02");
		param5.setSubtype(paramManager);
		
		em.persist(param1);
		em.persist(param2);
		em.persist(param3);
		em.persist(param4);
		em.persist(param5);
		em.persist(paramManager);
		em.persist(paramManager1);
		
		em.getTransaction().commit();		
		em.close();
//		
//		Slownik dic = new Slownik();
//		dic.setDescription("Słownik53");
//		dic.setName("Koniec53");
//		dic.setPublished(false);
//		dic.setDisplayOrder(12);
//		dic.setEntityDateTime(new EntityDateTime());
//		dic.setEntityLifeState(new EntityLifeState());
//		emRead.persist(dic);		
//		
//		emRead.getTransaction().begin();
//		for (int idx = 1; idx < 21; idx++) {
//			
//			
//			Country employee = emRead.find(Country.class, idx);							
//			if (Objects.isNull(employee)) {
//				//emRead.getTransaction().rollback();
//				continue;
//			}
//											
//			emRead.lock(employee, LockModeType.PESSIMISTIC_WRITE);					 				
//			employee.setName("andrzej" + idx);
//   		    employee.setDescription("????????????????????????");
//			employee.setPublished(false);
//			
//		}
//	
//		emRead.getTransaction().commit();
//		emRead.close();
	}
	
	/**
	 * @category constructor
	 */
	private static void departmentUpdate() {
		EntityManager em = entityManagerFactory.createEntityManager();
		for (int idx = 0; idx < 10000; idx++) {
			
			em.getTransaction()
				.begin();
			Country department = em.find(Country.class, 1);
			em.lock(department, LockModeType.PESSIMISTIC_READ);
			try {
				TimeUnit.SECONDS.sleep(1);
				// System.out.println("Has readed " + department);
				em.getTransaction()
					.commit();
			} catch (InterruptedException e) {
				e.printStackTrace();
				em.getTransaction()
					.getRollbackOnly();
			} finally {
				department = null;
				System.out.println("Has realesed "
						+ department);
			}
		}
		em.close();
	}
	
	public static void persistEmployee() {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction()
			.begin();
		
		LocalDate date = LocalDate.of(2017, 12, 31);
		
		for (var idx = 1; idx <= 1000; idx++) {
			
			date = date.plusDays(1L);

			
			CalendarPrimaryKey calendarKey = new CalendarPrimaryKey(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
			Calendar cla = new Calendar(calendarKey);
			cla.setName("Kalendarz"
					+ idx);
			
			CalendarDay cd = new CalendarDay(calendarKey);
			cd.setFirstDayInMonth(calendarKey.getDayNumber() == 1);
			cla.setCalendarDay(cd);
			
			//em.persist(cla);
		}
		em.getTransaction()
			.commit();
		em.close();
	}
	
	public static void main(final String[] args) throws InterruptedException {
		
		employeeUpdate();
		persistEmployee();
		
		// throws InterruptedException, ExecutionException, IOException, JAXBException {
		
		// Class<Product> cls = Product.class;
		// Metamodel model = em.getMetamodel();
		// EntityType<Product> entity = model.entity(cls);
		// CriteriaQuery<Product> c = cb.createQuery(cls);
		// Root<Product> account = c.from(entity);
		// Path<Integer> balance = account.<Integer>get("balance");
		// c.where(cb.and /
		// (c.greaterThan(balance, 100),
		// c.lessThan(balance), 200)
		// ));
		
//		ExecutorService es = Executors.newFixedThreadPool(4);
//		try {
//			es.execute(() -> {
//				// departmentUpdate();
//			});
//			es.execute(() -> {
//				employeeUpdate();
//				// simulating other user update
//			});
//			es.shutdown();
//			es.awaitTermination(1000, TimeUnit.SECONDS);
//		} finally {
//			entityManagerFactory.close();
//		}
//
//		GenericRepository<Country> prod = new GenericRepository<>(Country.class, "shopdb");
//		Optional<Country> c = prod.select(11);
//		if (c.isPresent())
//			c.get().getDisplayOrder();
		
		// prod.delete();
		// System.out.println(postCodes.getName());
		// byte[] picturePl =
		// Files.readAllBytes(Paths.get("D:/databases/flagi/pl.png"));
		//
//		Country country = new Country();
//
//		country.setName("Afganistan");
//		country.setTwoLetterIsoCode("AF");
//		country.setThreLetterIsoCode("AFG");
//		country.setNumericIsoCode(LocalTime.now().toString());
//		country.setDescription("Islamska Republika Afganistanu");
//		EntityLifeState dd = new EntityLifeState();
//		dd.setRecordInfo(EntityRowInfo.PENDING);
//		country.setEntityLifeState(dd);
		// prod.insert(country);
		// countries.save(country);
		// System.out.println(country.getIsoSequence());
		//
		// country = new Country();
		// country.setName("Albania");
		// country.setTwoLetterIsoCode("AL");
		// country.setThreLetterIsoCode("ALB");
		// country.setNumericIsoCode("008");
		// country.setDescription("Republika Albanii");
		// country.setEntityLifeState(dd);
		// countries.save(country);
		// CreditCard cd = new CreditCard();
		// cd.setCcNumber("Andrzej");
		// countries.save(country);
		//
		// curriences.add(currencyRUR);
		// country = new Country();
		// country.setPicture(picturePl);
		// countries.save(country);
		//
		// System.out.println(countries.countAsync());
		// country = countries.find(2).stream().findFirst().get();
		//
		// final GenericRepository<CalendarData> cals = new
		// GenericRepository<>(CalendarData.class, "db");
		// cals.removeAll();
		// out.println(cals.count());
		// System.exit(0);
		// Calendar cal = Calendar.getInstance(Locale.getDefault());
		// CalendarData calDta = new CalendarData();
		// calDta.setDate(Date.from(Instant.now()));
		// for (int idx = 0; idx < 21000; idx++) {
		// cal.add(Calendar.DATE, 1);
		// calDta.setDate(cal.getTime());
		// calDta.setYear(cal.get(Calendar.YEAR));
		// calDta.setDayNumInYear(cal.get(Calendar.DAY_OF_YEAR));
		// calDta.setDayNumInMonth(cal.get(Calendar.DAY_OF_MONTH));
		// calDta.setDayNumInWeek(cal.get(Calendar.DAY_OF_WEEK));
		// calDta.setMonthName(new SimpleDateFormat("MMMM").format(cal.getTime()));
		// calDta.setMonthNumInYear(cal.get(Calendar.MONTH) + 1);
		// calDta.setQuaterNum((cal.get(Calendar.MONTH) / 3) + 1);
		// cals.save(calDta);
		// }
		//
		// out.println(cals.count());
	}
	
	public static long dayOfQtr(final Instant date) {
		
		final LocalDate ld = LocalDate.from(date);
		final LocalDate firstDayOfQtr = LocalDate.of(ld.getYear(), ((TestDb.qtr(ld) - 1) * 3) + 1, 1);
		return ChronoUnit.DAYS.between(firstDayOfQtr, date) + 1;
	}
	
	public static int qtr(final LocalDate date) {
		
		return ((date.getMonthValue() - 1) / 3) + 1;
	}
	
}
