package com.students_management.data.entity;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User extends BaseEntity {

	private static final long serialVersionUID = -4118882973652546269L;

	@Embedded
	protected UserInfo userInfo;

	@Embedded
	protected AccountInfo accountInfo;
}
