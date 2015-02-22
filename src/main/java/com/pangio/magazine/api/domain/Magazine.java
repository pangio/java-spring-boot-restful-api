package com.pangio.magazine.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Domain Layer. Represents the POJO of an {@link Magazine}.
 * 
 * @author pangio
 */
@Entity
public class Magazine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
    @OneToMany(fetch=FetchType.EAGER, mappedBy="magazine")
    @Cascade({CascadeType.ALL})
    @JsonManagedReference
	private List<Article> articles = new ArrayList<Article>();

	/**
	 * {@link Magazine } default constructor required by JPA
	 */
	public Magazine() {
	}

	/**
	 * Constructor
	 * @param name of the magazine
	 */
	public Magazine(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		this.articles.add(article);
	}

}
