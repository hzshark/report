package cn.xp.report.service;

import cn.xp.report.dao.MachineMapper;
import cn.xp.report.model.MachineInfo;
import cn.xp.report.model.MachineItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MachineManageService {
    private static Logger logger = LoggerFactory.getLogger(MachineManageService.class);

    @Autowired
    private MachineMapper machineMapper;
    @Resource
    public JdbcTemplate jdbcTemplate;

    public PageInfo<MachineInfo> getMachineList(int pageNo, int pageSize,int uid,int mid){
        PageHelper.startPage(pageNo, pageSize);
        List<MachineInfo> groupList;
        if (mid>0)
         groupList= machineMapper.selectUserMachineListByMid(uid,mid);
        else
            groupList=machineMapper.selectUserMachineList(uid);
        PageInfo<MachineInfo> pageInfo= new PageInfo<MachineInfo>(groupList);
        return pageInfo;
    }

    public PageInfo<MachineItem> getSaleMachineList(int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<MachineItem> groupList = machineMapper.selectSaleMachineList();
        PageInfo<MachineItem> pageInfo= new PageInfo<MachineItem>(groupList);
        return pageInfo;
    }

    //@Transactional
    public int BuyMachine(int userId, int mid,double amount) {
        int ret = machineMapper.BuyMachine(userId,mid,amount);

        //String sp="Call SBuyMachine("+userId+","+mid+","+amount+")";
        //jdbcTemplate.execute(sp);
        return ret;
    }


    /*jdbcTemplate.execute(
            new CallableStatementCreator() {
        public CallableStatement createCallableStatement(Connection con) throws SQLException {
            String storedProc = "{call testpro(?,?)}";// 调用的sql
            CallableStatement cs = con.prepareCall(storedProc);
            cs.setString(1, "p1");// 设置输入参数的值
            cs.registerOutParameter(2, OracleTypes.VARCHAR);// 注册输出参数的类型
            return cs;
        }
    }, new CallableStatementCallback() {
        public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
            cs.execute();
            return cs.getString(2);// 获取输出参数的值
        }
    });*/

}
