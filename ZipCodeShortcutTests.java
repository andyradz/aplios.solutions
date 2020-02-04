package com.codigo.aplios.group.database.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.group.database.models.location.ZipCodeShortcut;
import com.codigo.aplios.group.database.repository.GenericRepository;

public class ZipCodeShortcutTests {

	private static final String DabataseName = "shopdb";

	@BeforeEach
	public void setUp() throws Exception {

		try (GenericRepository<ZipCodeShortcut> repo = new GenericRepository<>(ZipCodeShortcut.class, DabataseName)) {

			System.out.println("Usunięto "
					+ repo.deleteAll());

			assert repo.count() == 0 : "Oczekiwano brak rekordów w zbiorze  ZipCodeShortcut";

			ZipCodeShortcut zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Al.");
			zipShortcut.setName("Aleja");
			zipShortcut.setDescription("Aleja");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Ale.");
			zipShortcut.setName("Aleje");
			zipShortcut.setDescription("Aleje");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Alk.");
			zipShortcut.setName("Alejka");
			zipShortcut.setDescription("Alejka");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("AP");
			zipShortcut.setName("Agencja Pocztowa");
			zipShortcut.setDescription("Agencja Pocztowa");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Bast.");
			zipShortcut.setName("Bastion");
			zipShortcut.setDescription("Bastion");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Bulw.");
			zipShortcut.setName("Bulwar");
			zipShortcut.setDescription("Bulwar");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Bulwy.");
			zipShortcut.setName("Bulwary");
			zipShortcut.setDescription("Bulwary");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Dep.");
			zipShortcut.setName("Deptak");
			zipShortcut.setDescription("Deptak");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("DK");
			zipShortcut.setName("Do końca");
			zipShortcut.setDescription("Do końca");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Dol.");
			zipShortcut.setName("Dolina");
			zipShortcut.setDescription("Dolina");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("FUP");
			zipShortcut.setName("Filia Urzędu Pocztowego");
			zipShortcut.setDescription("Filia Urzędu Pocztowego");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Gm.");
			zipShortcut.setName("Gmina");
			zipShortcut.setDescription("Gmina");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Jez.");
			zipShortcut.setName("Jezioro");
			zipShortcut.setDescription("Jezioro");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Kol.");
			zipShortcut.setName("Kolonia");
			zipShortcut.setDescription("Kolonia");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Kole.");
			zipShortcut.setName("Kolonie");
			zipShortcut.setDescription("Kolonie");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("n");
			zipShortcut.setName("Numery nieparzyste");
			zipShortcut.setDescription("Numery nieparzyste");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Os.");
			zipShortcut.setName("Osiedle");
			zipShortcut.setDescription("Osiedle");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Oś.");
			zipShortcut.setName("Ośrodek");
			zipShortcut.setDescription("Ośrodek");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("p");
			zipShortcut.setName("Liczby parzyste");
			zipShortcut.setDescription("Liczby parzyste");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Pl.");
			zipShortcut.setName("Plac");
			zipShortcut.setDescription("Plac");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("PH");
			zipShortcut.setName("Przedstawicielstwo Handlowe");
			zipShortcut.setDescription("Przedstawicielstwo Handlowe");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Pol.");
			zipShortcut.setName("Polana");
			zipShortcut.setDescription("Polana");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Prom.");
			zipShortcut.setName("Promenada");
			zipShortcut.setDescription("Promenada");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Przeł.");
			zipShortcut.setName("Przełęcz");
			zipShortcut.setDescription("Przełęcz");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("RH");
			zipShortcut.setName("Region Handlowy");
			zipShortcut.setDescription("Region Handlowy");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Schr.");
			zipShortcut.setName("Schronisko");
			zipShortcut.setDescription("Schronisko");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Skw.");
			zipShortcut.setName("Skwer");
			zipShortcut.setDescription("Skwer");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Skwe.");
			zipShortcut.setName("Skwerek");
			zipShortcut.setDescription("Skwerek");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Targ.");
			zipShortcut.setName("Targowisko");
			zipShortcut.setDescription("Targowisko");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Ul.");
			zipShortcut.setName("Ulica");
			zipShortcut.setDescription("Ulica");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("UP");
			zipShortcut.setName("Urząd Pocztowy");
			zipShortcut.setDescription("Urząd Pocztowy");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Wybrz.");
			zipShortcut.setName("Wybrzeże");
			zipShortcut.setDescription("Wybrzeże");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Wzg.");
			zipShortcut.setName("Wzgórze");
			zipShortcut.setDescription("Wzgórze");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Wzga.");
			zipShortcut.setName("Wzgórza");
			zipShortcut.setDescription("Wzgórza");
			repo.insert(zipShortcut);

			zipShortcut = new ZipCodeShortcut();
			zipShortcut.setCode("Źr.");
			zipShortcut.setName("Źródło");
			zipShortcut.setDescription("Źródło");
			repo.insert(zipShortcut);
		}
	}

	@Test
	public void checkAvailablePeople() throws IOException, InterruptedException, ExecutionException {

		try (GenericRepository<ZipCodeShortcut> storeDb =
				new GenericRepository<>(ZipCodeShortcut.class, DabataseName)) {

			final var itemsCount = storeDb.countAsync();

			assertThat(itemsCount, is(35L));

			final Query q = storeDb.getQuery("select m from ZipCodeShortcut m");
			q.setLockMode(LockModeType.NONE);
			assertThat(q.getResultList().size(), is(35));
		}
	}

	@Test
	public void checkFamily() {

//	        EntityManager em = factory.createEntityManager();
//	        // Go through each of the entities and print out each of their
//	        // messages, as well as the date on which it was created
//	        Query q = em.createQuery("select f from Family f");
//
//	        // We should have one family with 40 persons
//	        assertTrue(q.getResultList().size() == 1);
//	        assertTrue(((Family) q.getSingleResult()).getMembers().size() == 40);
//	        em.close();
	}

	// @Test(expected = javax.persistence.NoResultException.class)
	public void deletePerson() {

//	        EntityManager em = factory.createEntityManager();
//	        // Begin a new local transaction so that we can persist a new entity
//	        em.getTransaction().begin();
//	        Query q = em
//	                .createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName");
//	        q.setParameter("firstName", "Jim_1");
//	        q.setParameter("lastName", "Knopf_!");
//	        Person user = (Person) q.getSingleResult();
//	        em.remove(user);
//	        em.getTransaction().commit();
//	        Person person = (Person) q.getSingleResult();
//	        // Begin a new local transaction so that we can persist a new entity
//
//	        em.close();
	}

}
