package com.chenxiaojie.college.print.dao.api

import com.chenxiaojie.college.print.dao.aop.DAOAnnotation
import com.chenxiaojie.college.print.dao.driver.MybatisExtendedLanguageDriver
import com.chenxiaojie.college.print.dao.entity.CollegeEntity
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Lang
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.Update

/**
 * Created by xiaojie.chen on 2015-03-04 15:39:38.
 */
interface CollegeDAO {

    @Insert("""
            INSERT INTO CP_College(CollegeName, CollegeAddress)
            VALUES(#{collegeName}, #{collegeAddress})
            """)
    @SelectKey(before = false, resultType = Integer.class, keyProperty = "id", statement = "SELECT LAST_INSERT_ID() AS id")
    void insert(CollegeEntity college)


    @Lang(MybatisExtendedLanguageDriver.class)
    @Insert("""
            INSERT INTO CP_College(CollegeName, CollegeAddress)
            VALUES(#{colleges.collegeName}, #{colleges.collegeAddress})
            """)
    int inserts(@Param("colleges") List<CollegeEntity> colleges)


    @Insert("""<script>
            INSERT INTO CP_College(CollegeName,CollegeAddress) VALUES
            <foreach collection="collegeAddresses" item="collegeAddress" separator=",">
            (#{collegeName},#{collegeAddress})
            </foreach>
            </script>""")
    int inserts2(@Param("collegeName") String collegeName, @Param("collegeAddresses") String[] collegeAddresses)


    @Delete("""
            DELETE
            FROM CP_College
            WHERE ID=#{id}
            """)
    int deleteById(@Param("id") int id)


    @Update("""<script>
            UPDATE CP_College SET ID=ID
            <if test="collegeName != null and collegeName != ''">
            ,CollegeName=#{collegeName}
            </if>
            <if test="collegeAddress != null and collegeAddress != ''">
            ,CollegeAddress=#{collegeAddress}
            </if>
            WHERE ID=#{id}
            </script>""")
    int update(CollegeEntity college)


    @Select("""
            SELECT ID, CollegeName, CollegeAddress
            FROM CP_College
            WHERE ID = #{id}
            """)
    CollegeEntity selectById(@Param("id") int id)


    @Lang(MybatisExtendedLanguageDriver.class)
    @Select("""
            SELECT ID, CollegeName, CollegeAddress
            FROM CP_College
            WHERE ID IN (#{ids})
            """)
    List<CollegeEntity> selectByIds(@Param("ids") List<Integer> ids);

    @DAOAnnotation
    @Select("""
            SELECT ID, CollegeName, CollegeAddress
            FROM CP_College
            limit #{offset}, #{limit}
            """)
    List<CollegeEntity> selectByPage(@Param("offset") int offset, @Param("limit") int limit)


    @Select("""
            SELECT COUNT(1)
            FROM CP_College
            """)
    int countByPage()
}