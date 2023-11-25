package com.example.connect.connections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ConnectionsRepo extends JpaRepository<Connections, Long> {
    List<Connections> findByFirst_idOrSecond_id(@NonNull Long first_id, @NonNull Long second_id);

}
