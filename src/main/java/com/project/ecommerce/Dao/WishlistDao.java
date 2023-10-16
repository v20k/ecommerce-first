package com.project.ecommerce.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entity.Wishlist;
import com.project.ecommerce.repository.WishlistRepo;

@Repository
public class WishlistDao {

	@Autowired
	private WishlistRepo wishlistRepo;
	
	public Wishlist saveWishlist(Wishlist wishlist) {
		// TODO Auto-generated method stub
		return wishlistRepo.save(wishlist);
	}

	public Wishlist findWishlist(long id) {
		// TODO Auto-generated method stub
		Optional<Wishlist> optional = wishlistRepo.findById(id);
		if(optional.isEmpty()){
			return null;
		}
		return optional.get();
	}

	public void deleteWishlist(long id) {
		// TODO Auto-generated method stub
		wishlistRepo.deleteById(id);
	}
}
