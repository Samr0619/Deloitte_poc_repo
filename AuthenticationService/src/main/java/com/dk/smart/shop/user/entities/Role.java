//package com.dk.smart.shop.user.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// * @author DurgaKalyan
// * @version 1.0
// *
// */
//@Entity
//@Table(name = "role")
//public class Role {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int id;
//	
//	private String name;
//	
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	
//	public Role() {
//		
//	}
//	
//	public Role(int id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//	
//	
//	@Override
//	public String toString() {
//		return "Role [id=" + id + ", name=" + name + "]";
//	}
//
//}