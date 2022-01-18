#if($orm == 'xml')
__REMOVE_ME__
#end
#set( $rootAggregateUpperCase = $rootAggregate.toUpperCase() )
package ${package}.infrastructure.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
#if ($database == 'h2' || $database == 'postgresql')
import javax.persistence.Table;
#end
#if ($database == 'mongo')
import org.springframework.data.mongodb.core.mapping.Document;
#end

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};

/**
 * {@link ${rootAggregate}} entity.
 */
@Entity
#if ($database == 'mongo')
@Document
#else
@Table(name = "T_${rootAggregateUpperCase}")
#end
public class ${rootAggregate}Entity {
    /**
     * ${rootAggregate} technical identifier. Must be not null.
     */
    @Id
    private String id;
    
    @Column(nullable = false, length = 20)
    private String firstName;
    
    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = true, length = 10)
    private String birthDate;

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the Unique ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param birthDate the lastName to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the lastName
     */
    public String getBirthDate() {
        return birthDate;
    }

    public ${rootAggregate}Entity(){}

    public ${rootAggregate}Entity(String uid, String firstName, String lastName, String birthDate) {
        this.id = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }



}
