package top.kinglf.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.kinglf.dataservice.common.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
