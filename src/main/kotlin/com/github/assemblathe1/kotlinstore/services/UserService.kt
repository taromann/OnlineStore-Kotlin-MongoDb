package com.github.assemblathe1.kotlinstore.services

import com.github.assemblathe1.kotlinstore.dto.Cart
import com.github.assemblathe1.kotlinstore.entities.Role
import com.github.assemblathe1.kotlinstore.entities.User
import com.github.assemblathe1.kotlinstore.exceptions.exceptions.ResourceNotFoundException
import com.github.assemblathe1.kotlinstore.repositories.RoleRepository
import com.github.assemblathe1.kotlinstore.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.MergeOperation.UniqueMergeId.id
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.print.Doc


@Service
class UserService @Autowired constructor(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val mongoTemplate: MongoTemplate
) : UserDetailsService {
    fun User.getRoles() = this.rolesNames.map {
        roleRepository.findRoleByTitle(it)
//        mongoTemplate.findOne(
//            Query.query(Criteria.where("name").`is`(it)),
//            Role::class.java
//        )
    }.toList()

    @Override
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = findByUsername(username)
        return org.springframework.security.core.userdetails.User(
            user.userName,
            user.password,
            mapRolesToAuthorities(user.getRoles())
        )
    }
    fun findByUsername(username: String): User {
        userRepository.findByUsername(username)?.let { return it } ?: throw ResourceNotFoundException("User was not found")
    }
    private fun mapRolesToAuthorities(roles: List<Role?>) =
        roles.map { SimpleGrantedAuthority(it?.name) }.toSet()

    fun updateCartForUser(username: String, currentCart: Cart) {
        val query: Query = Query().addCriteria(where("userName").`is`(username))
        val update = Update()
        update["cart"] = currentCart
        mongoTemplate.update(User::class.java).matching(query).apply(update).first()
    }
}