// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: accounts.proto

package net.dracones.bankng;

public interface ListTransactionsResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:net.dracones.bankng.ListTransactionsResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .net.dracones.bankng.Transaction transactions = 1;</code>
   */
  java.util.List<net.dracones.bankng.Transaction> 
      getTransactionsList();
  /**
   * <code>repeated .net.dracones.bankng.Transaction transactions = 1;</code>
   */
  net.dracones.bankng.Transaction getTransactions(int index);
  /**
   * <code>repeated .net.dracones.bankng.Transaction transactions = 1;</code>
   */
  int getTransactionsCount();
  /**
   * <code>repeated .net.dracones.bankng.Transaction transactions = 1;</code>
   */
  java.util.List<? extends net.dracones.bankng.TransactionOrBuilder> 
      getTransactionsOrBuilderList();
  /**
   * <code>repeated .net.dracones.bankng.Transaction transactions = 1;</code>
   */
  net.dracones.bankng.TransactionOrBuilder getTransactionsOrBuilder(
      int index);
}