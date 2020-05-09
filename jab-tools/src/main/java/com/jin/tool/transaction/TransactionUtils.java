package com.jin.tool.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.function.Consumer;

/**
 * 基础事务服务
 *  编程提交事务 教程
 *  TransactionUtils.transact(Propagation.REQUIRES_NEW, s -> this.baseMapper.insert(object));
 */
@Component
@Slf4j
public class TransactionUtils {

    private static PlatformTransactionManager transactionManager;

    static TransactionDefinition  transactionDefinition;

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        TransactionUtils.transactionManager = transactionManager;
    }
    @Autowired
    public void setTransactionDefinition(TransactionDefinition transactionDefinition) {
        TransactionUtils.transactionDefinition = transactionDefinition;
    }

    /**
     * 运行线程
     * @param consumer
     * @return
     */
    public static void transact(Consumer consumer) {
        transact( transactionDefinition, consumer);
    }

    /**
     *  运行事务
     * @param propagation 传播类型
     * @param consumer
     * @return
     */
    public static void transact(Propagation propagation, Consumer consumer) {
        DefaultTransactionDefinition  transactionDefinition = new DefaultTransactionDefinition(propagation.value());
        transact(transactionDefinition , consumer);
    }

    public static void transact(TransactionDefinition  transactionDefinition,Consumer consumer) {
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            consumer.accept(null);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("提交事务失败e:{}",e);
            throw new TransactionUsageException("提交事务失败",e);
        }
    }

}
