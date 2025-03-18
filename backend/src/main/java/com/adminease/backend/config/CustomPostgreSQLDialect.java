package com.adminease.backend.config;


import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class CustomPostgreSQLDialect extends PostgreSQLDialect {
    public CustomPostgreSQLDialect() {
        super();
        registerFunction("unaccent", new StandardSQLFunction("unaccent"));
    }
}