package com.example.tibia.model.mappers

abstract class EntityMapper<Entity, DomainModel> {
    abstract fun mapFromEntity(entity: Entity): DomainModel
    abstract fun mapToEntity(domainModel: DomainModel): Entity

    fun fromEntityList(initial: List<Entity>):  List<DomainModel> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<DomainModel>): List<Entity>{
        return initial.map {mapToEntity(it)}
    }
}