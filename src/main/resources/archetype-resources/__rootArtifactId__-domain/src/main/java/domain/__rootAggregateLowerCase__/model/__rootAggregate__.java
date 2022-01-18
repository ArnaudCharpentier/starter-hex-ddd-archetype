package ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model;

import ${package}.domain.common.ddd.DDD;
import ${package}.domain.common.ddd.BaseAggregateRoot;
import ${package}.domain.common.model.UniqueId;
import ${package}.domain.validation.annotations.IsMajeur;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * ${rootAggregate} Domain Root Aggregate.
 * 
 */
@DDD.AggregateRoot
public class ${rootAggregate} extends BaseAggregateRoot<${rootAggregate}, UniqueId> {

    /**
     * These are automatically created properties
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @IsMajeur
    private LocalDate birthdate;

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public LocalDate getBirthDate(){
        return this.birthdate;
    }

    public static ${rootAggregate}.Builder builder(){
        return new ${rootAggregate}.Builder();
    }

    private ${rootAggregate}(${rootAggregate}.Builder builder){
        super(${rootAggregate}.class, (builder.id!=null)?builder.id:new UniqueId());
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthdate = builder.birthdate;
        validate(this);
    }

    #if($orm == 'xml')
    /* 
     *  ORM need private constructor 
     *  Don't use it in production environement
     */
    private ${rootAggregate}(){
        super(${rootAggregate}.class);
        this.lastName = null;
        this.firstName = null;
        this.birthdate = null;
    }
    #end

    public static class Builder{

        private UniqueId id;
        private String firstName;
        private String lastName;
        private LocalDate birthdate;

        public Builder id(UniqueId id){
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate){
            this.birthdate = birthDate;
            return this;
        }

        public ${rootAggregate} build(){
            return new ${rootAggregate}(this);
        }
    }
}
