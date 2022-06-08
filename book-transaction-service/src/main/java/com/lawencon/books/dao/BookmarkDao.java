package com.lawencon.books.dao;

import com.lawencon.books.model.Bookmark;
import com.lawencon.books.pojo.PojoBooks;

public interface BookmarkDao extends BaseMasterDao<Bookmark>{
	PojoBooks getIdBookFromBookmark(String idUser);
}
