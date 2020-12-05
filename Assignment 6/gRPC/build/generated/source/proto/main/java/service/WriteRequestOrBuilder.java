// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services/story.proto

package service;

public interface WriteRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:services.WriteRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * A new sentence that will be added to the old ones, to build a story. 
   * </pre>
   *
   * <code>string new_sentence = 2;</code>
   * @return The newSentence.
   */
  java.lang.String getNewSentence();
  /**
   * <pre>
   * A new sentence that will be added to the old ones, to build a story. 
   * </pre>
   *
   * <code>string new_sentence = 2;</code>
   * @return The bytes for newSentence.
   */
  com.google.protobuf.ByteString
      getNewSentenceBytes();
}