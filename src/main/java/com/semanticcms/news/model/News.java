/*
 * semanticcms-news-model - SemanticCMS newsfeeds.
 * Copyright (C) 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-news-model.
 *
 * semanticcms-news-model is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-news-model is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-news-model.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.news.model;

import com.semanticcms.core.model.Element;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

public class News extends Element implements Comparable<News> {

	// Target of news entry, will be the parent page/element of the news entry when not specified
	private String book;
	private String targetPage;
	private String element;
	private String view;
	// The title of the news entry, will be the target page title/element label when not specified
	private String title;
	// The optional description of the news entry
	private String description;
	// TODO: comments once a comment system is enabled
	// guid: generated from target
	// Java 1.8: No longer use joda time
	private DateTime pubDate; // Required, maybe a future version could interact with versioning systems

	/**
	 * Ordered by pubDate desc, page
	 */
	@Override
	public int compareTo(News o) {
		int diff = pubDate.compareTo(o.pubDate);
		if(diff != 0) return diff;
		return getPage().compareTo(o.getPage());
	}

	@Override
	public News freeze() {
		super.freeze();
		return this;
	}

	@Override
	public String getLabel() {
		return title;
	}

	/**
	 * News elements are not part of the content directly, so are hidden from tree views.
	 */
	@Override
	public boolean isHidden() {
		return true;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		checkNotFrozen();
		this.book = book==null || book.isEmpty() ? null : book;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		checkNotFrozen();
		this.targetPage = targetPage==null || targetPage.isEmpty() ? null : targetPage;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		checkNotFrozen();
		this.element = element==null || element.isEmpty() ? null : element;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		checkNotFrozen();
		this.view = view==null || view.isEmpty() ? null : view;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		checkNotFrozen();
		this.title = title==null || title.isEmpty() ? null : title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		checkNotFrozen();
		this.description = description==null || description.isEmpty() ? null : description;
	}

	public DateTime getPubDate() {
		return pubDate;
	}

	public void setPubDate(ReadableDateTime pubDate) {
		checkNotFrozen();
		this.pubDate = pubDate==null ? null : pubDate.toDateTime();
	}

	@Override
	protected String getDefaultIdPrefix() {
		return "news";
	}
}
