package com.lawencon.books.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.books.dao.ReviewDao;
import com.lawencon.books.dto.review.GetReviewDtoData;
import com.lawencon.books.model.Review;

@Repository
public class ReviewDaoImpl extends BaseDaoImpl implements ReviewDao {

	@Override
	public Review insert(Review data) {
		return super.saveInsert(data);
	}

	@Override
	public Review update(Review data) {
		return super.saveUpdate(data);
	}

	@Override
	public Review getById(String id) {
		return super.getById(Review.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Review.class, id);
	}

	@Override
	public List<Review> getAll() {
		List<Review> data = em.createQuery("From Review",Review.class).getResultList();
		return data;
	}

	@Override
	public List<GetReviewDtoData> getReviewByIdBook(String idBook) {
		String sql = "select tr.id , tr.rating , tr.book_comment , tr.id_book , tr.created_by from t_review tr where tr.id_book = :idBook";
		
		List<?> resultQuery = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.getResultList();
		
		List<GetReviewDtoData> result = new ArrayList<GetReviewDtoData>();
		
		resultQuery.stream().forEach(list -> {
			Object[] obj = (Object[]) list;
			GetReviewDtoData data = new GetReviewDtoData();
			data.setId(obj[0].toString());
			data.setRating(Integer.valueOf(obj[1].toString()));
			data.setBookComment(obj[2].toString());
			data.setIdBook(obj[3].toString());
			data.setCreatedBy(obj[4].toString());
			
			result.add(data);
		});
		
		return result;
	}
	
	@Override
	public BigDecimal getAvgRarting(String idBook) {
		String sql = "select round(avg(tr.rating),2) from t_review tr where tr.id_book = :idBook";
		Object result = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.getSingleResult();
		return (BigDecimal) result;
	}
	
	@Override
	public BigInteger getCountReview(String idBook) {
		String sql = "select count(tr.id) from t_review tr where tr.id_book = :idBook ";
		Object result = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.getSingleResult();
		return (BigInteger) result;
	}
}
