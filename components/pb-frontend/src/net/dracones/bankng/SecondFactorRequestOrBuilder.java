// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auth.proto

package net.dracones.bankng;

public interface SecondFactorRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:net.dracones.bankng.SecondFactorRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string session_id = 1;</code>
   * @return The sessionId.
   */
  java.lang.String getSessionId();
  /**
   * <code>string session_id = 1;</code>
   * @return The bytes for sessionId.
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();

  /**
   * <code>string code = 2;</code>
   * @return The code.
   */
  java.lang.String getCode();
  /**
   * <code>string code = 2;</code>
   * @return The bytes for code.
   */
  com.google.protobuf.ByteString
      getCodeBytes();
}