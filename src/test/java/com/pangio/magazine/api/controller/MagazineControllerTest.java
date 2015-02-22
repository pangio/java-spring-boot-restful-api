package com.pangio.magazine.api.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.pangio.magazine.api.Application;
import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.enums.ArticleType;
import com.pangio.magazine.api.factory.AbstractArticleFactory;
import com.pangio.magazine.api.repository.ArticleRepository;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * {@link MagazineControllerTest} 
 * Tests all the the {@link HttpMethod}s under the URI '/magazines'.
 * Includes  GET, POST, PUT, DELETE
 * @author pangio
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MagazineControllerTest {

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<Magazine> magazineList = new ArrayList<Magazine>();

	private List<Article> articleList = new ArrayList<Article>();

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays
				.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		emptyRepositories();
		createMagazines();
		createSportArticles();
		createPoliticArticles();
		addSportArticles();
		addPoliticArticles();
	}

	@Test
	public void createMagazine() throws Exception {
		String magazineJson = json(new Magazine("Winter Sports!"));
		this.mockMvc.perform(
				post("/magazines").contentType(contentType).content(
						magazineJson)).andExpect(status().isCreated());
	}
	
	@Test
	public void updateMagazine() throws Exception {
		Magazine magazine = this.magazineRepository.findByName("EU Politics");
//		Magazine magazine = this.magazineList.get(0);
		magazine.setName("NEW VERSION MAGAZINE");
		String magazineJson = json(magazine);
		mockMvc.perform(
				put("/magazines/"+magazine.getId().intValue())
				.contentType(contentType)
				.content(magazineJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("NEW VERSION MAGAZINE")));
	}
	
	@Test
	public void deleteMagazine() throws Exception {
		Magazine magazine = this.magazineList.get(0);
		mockMvc.perform(
				delete("/magazines/"+magazine.getId().intValue())
				.contentType(contentType))
//				.andExpect(content().contentType(contentType))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteArticleFromMagazine() throws Exception {
		Magazine magazine = this.magazineList.get(0);
		Article article = magazine.getArticles().get(0);
		mockMvc.perform(
				delete("/magazines/" + magazine.getId().intValue() + "/articles/" + article.getId().intValue())
				.contentType(contentType))
//				.andExpect(content().contentType(contentType))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldNotDeleteMagazine() throws Exception {
		mockMvc.perform(
				delete("/magazines/799879899")
				.contentType(contentType))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(contentType));
	}
		
	@Test
	public void shouldNotToDeleteArticleFromMagazine() throws Exception {
		Magazine magazine = this.magazineList.get(0);
		mockMvc.perform(
				delete("/magazines/" + magazine.getId()+ "/articles/799879899")
				.contentType(contentType))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void createPoliticArticle() throws Exception {
		
		AbstractArticleFactory abstractFactory;
		abstractFactory = AbstractArticleFactory.getFactory(ArticleType.POLITIC);		
		Article politicArticle = abstractFactory.createArticle();
		politicArticle.setTitle("Obama");
		politicArticle.setContent("Something about Obama");
		this.articleList.add(this.articleRepository.save(politicArticle));
		String politicArticleJson = json(politicArticle);
		Magazine magazine = this.magazineList.get(1);

		this.mockMvc
				.perform(
						post(
								"/magazines/" + magazine.getId()
										+ "/politic").contentType(
								contentType).content(politicArticleJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title", is("Obama")))
				.andExpect(jsonPath("$.content", is("Something about Obama")));
	}

	@Test
	public void shouldNotToCreatePoliticArticle() throws Exception {
		
		AbstractArticleFactory abstractFactory;
		abstractFactory = AbstractArticleFactory.getFactory(ArticleType.POLITIC);		
		Article politicArticle = abstractFactory.createArticle();
		politicArticle.setTitle("Obama");
		this.articleList.add(this.articleRepository.save(politicArticle));
		String politicArticleJson = json(politicArticle);
		this.mockMvc
		.perform(
				post(
						"/magazines/799879899"
						+ "/politic").contentType(
								contentType).content(politicArticleJson))
								.andExpect(status().isNotFound());
	}
	
	@Test
	public void createSportArticle() throws Exception {
		
		AbstractArticleFactory abstractFactory;
		abstractFactory = AbstractArticleFactory.getFactory(ArticleType.SPORT);		
		Article sportArticle = abstractFactory.createArticle();
		sportArticle.setTitle("Messi");
		sportArticle.setContent("Something about Messi");
		String sportArticleJson = json(sportArticle);
		Magazine magazine = this.magazineList.get(0);

		this.mockMvc.perform(
				post("/magazines/" + magazine.getId() + "/sport")
				.contentType(contentType)
				.content(sportArticleJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title", is("Messi")))
				.andExpect(jsonPath("$.content", is("Something about Messi")));
	}

	@Test
	public void readSingleMagazine() throws Exception {

		Magazine magazine = this.magazineList.get(0);
		mockMvc.perform(
				get("/magazines/" + magazine.getId())
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(magazine.getId().intValue())))
				.andExpect(jsonPath("$.name", is("Soccer Magazine!")));
	}

	@Test
	public void readMagazines() throws Exception {
		mockMvc.perform(get("/magazines"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(
						jsonPath("$[0].id", is(this.magazineList.get(0).getId()
								.intValue())))
				.andExpect(jsonPath("$[0].name", is("Soccer Magazine!")))
				.andExpect(
						jsonPath("$[1].id", is(this.magazineList.get(1).getId()
								.intValue())))
				.andExpect(jsonPath("$[1].name", is("EU Politics")));
	}

	@Test
	public void magazineNotFound() throws Exception {
		mockMvc.perform(get("/magazines/799879899"))
		.andExpect(status().isNotFound())
		.andExpect(content().contentType(contentType));
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o,
				MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
	
	private void addPoliticArticles() {
		Magazine euPoliticsMagazine = magazineRepository.findByName("EU Politics");
		euPoliticsMagazine.setArticles(
				Arrays.asList(
				articleRepository.findByTitle("Ireland"),
				articleRepository.findByTitle("UK"),
				articleRepository.findByTitle("Germany"))
				);
		this.magazineRepository.save(euPoliticsMagazine);
	}

	private void addSportArticles() {
		Magazine soccerMagazine = this.magazineRepository.findByName("Soccer Magazine!");
		soccerMagazine.setArticles(Arrays.asList(
				articleRepository.findByTitle("Maradona"),
				articleRepository.findByTitle("Pele"),
				articleRepository.findByTitle("Ronaldo")));
		this.magazineRepository.save(soccerMagazine);
	}

	private void createMagazines() {
		Magazine sportMagazine = new Magazine("Soccer Magazine!");
		this.magazineRepository.save(sportMagazine);
		this.magazineList.add(this.magazineRepository.save(sportMagazine));
		Magazine politicMagazine = new Magazine("EU Politics");
		this.magazineList.add(this.magazineRepository.save(politicMagazine));
		this.magazineRepository.save(politicMagazine);
	}

	private void createSportArticles() {
		// temporary list that will store articles for the Sport magazine
		ArrayList<Article> sportArticleList = new ArrayList<Article>();
		
		AbstractArticleFactory abstractFactory;
	    abstractFactory = AbstractArticleFactory.getFactory(ArticleType.SPORT);

	    Article sportArticle = abstractFactory.createArticle();
	    sportArticle.setTitle("Maradona");
		this.articleList.add(this.articleRepository.save(sportArticle));
		sportArticleList.add(sportArticle); // adding to temporary list

		sportArticle = abstractFactory.createArticle();
		sportArticle.setTitle("Pele");
		this.articleList.add(this.articleRepository.save(sportArticle));
		sportArticleList.add(sportArticle); // adding to temporary list
		
		sportArticle = abstractFactory.createArticle();
		sportArticle.setTitle("Ronaldo");
		this.articleList.add(this.articleRepository.save(sportArticle));
		sportArticleList.add(sportArticle); // adding to temporary list
		
		this.magazineList.get(0).setArticles(sportArticleList);
	}
	
	private void createPoliticArticles() {
		// temporary list that will store articles for the Politic magazine
		ArrayList<Article> politicArticleList = new ArrayList<Article>();

		AbstractArticleFactory abstractFactory;
		abstractFactory = AbstractArticleFactory.getFactory(ArticleType.POLITIC);
		
		Article politicArticle = abstractFactory.createArticle();
		politicArticle.setTitle("UK");
		this.articleList.add(this.articleRepository.save(politicArticle));
		politicArticleList.add(politicArticle); // adding to temporary list
		
		politicArticle = abstractFactory.createArticle();
		politicArticle.setTitle("Germany");
		this.articleList.add(this.articleRepository.save(politicArticle));
		politicArticleList.add(politicArticle); // adding to temporary list

		politicArticle = abstractFactory.createArticle();
		politicArticle.setTitle("Ireland");
		this.articleList.add(this.articleRepository.save(politicArticle));
		politicArticleList.add(politicArticle); // adding to temporary list

		this.magazineList.get(1).setArticles(politicArticleList);

	}

	private void emptyRepositories() {
		this.articleRepository.deleteAllInBatch();
		this.magazineRepository.deleteAllInBatch();
	}
}
