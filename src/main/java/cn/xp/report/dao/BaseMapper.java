package cn.xp.report.dao;

public interface BaseMapper<T, I> {

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(I id);

    /**
     * 新写入数据库记录
     *
     * @param record
     */
    int insert(T record);

    /**
     * 动态字段,写入数据库记录
     *
     * @param record
     */
    int insertSelective(T record);

    /**
     * 根据指定主键获取一条数据库记录
     *
     * @param id
     */
    T selectByPrimaryKey(I id);

    /**
     * 动态字段,根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(T record);
}
