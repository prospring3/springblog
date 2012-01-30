/**
 * Created on Oct 21, 2011
 */
package com.apress.prospring3.springblog.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Clarence
 *
 */
@Entity
@Table(name = "entry_rating")
public class EntryRating implements Serializable {

	private Long id;
	private Entry entry;
	private int rating;
	private String rateBy;

	public EntryRating() {
	}

	public EntryRating(Entry entry, int rating, String rateBy) {
		this.entry = entry;
		this.rating = rating;
		this.rateBy = rateBy;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTRY_ID")
	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	@Column(name = "RATING")
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "RATE_BY")
	public String getRateBy() {
		return this.rateBy;
	}

	public void setRateBy(String rateBy) {
		this.rateBy = rateBy;
	}

}
