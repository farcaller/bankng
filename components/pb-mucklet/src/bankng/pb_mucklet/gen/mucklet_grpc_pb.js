// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('@grpc/grpc-js');
var mucklet_pb = require('./mucklet_pb.js');
var google_protobuf_empty_pb = require('google-protobuf/google/protobuf/empty_pb.js');

function serialize_bankng_mucklet_LookupCharacterReply(arg) {
  if (!(arg instanceof mucklet_pb.LookupCharacterReply)) {
    throw new Error('Expected argument of type bankng.mucklet.LookupCharacterReply');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_bankng_mucklet_LookupCharacterReply(buffer_arg) {
  return mucklet_pb.LookupCharacterReply.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_bankng_mucklet_LookupCharacterRequest(arg) {
  if (!(arg instanceof mucklet_pb.LookupCharacterRequest)) {
    throw new Error('Expected argument of type bankng.mucklet.LookupCharacterRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_bankng_mucklet_LookupCharacterRequest(buffer_arg) {
  return mucklet_pb.LookupCharacterRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_bankng_mucklet_SendMessageRequest(arg) {
  if (!(arg instanceof mucklet_pb.SendMessageRequest)) {
    throw new Error('Expected argument of type bankng.mucklet.SendMessageRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_bankng_mucklet_SendMessageRequest(buffer_arg) {
  return mucklet_pb.SendMessageRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_google_protobuf_Empty(arg) {
  if (!(arg instanceof google_protobuf_empty_pb.Empty)) {
    throw new Error('Expected argument of type google.protobuf.Empty');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_google_protobuf_Empty(buffer_arg) {
  return google_protobuf_empty_pb.Empty.deserializeBinary(new Uint8Array(buffer_arg));
}


var MuckletService = exports.MuckletService = {
  lookupCharacter: {
    path: '/bankng.mucklet.Mucklet/LookupCharacter',
    requestStream: false,
    responseStream: false,
    requestType: mucklet_pb.LookupCharacterRequest,
    responseType: mucklet_pb.LookupCharacterReply,
    requestSerialize: serialize_bankng_mucklet_LookupCharacterRequest,
    requestDeserialize: deserialize_bankng_mucklet_LookupCharacterRequest,
    responseSerialize: serialize_bankng_mucklet_LookupCharacterReply,
    responseDeserialize: deserialize_bankng_mucklet_LookupCharacterReply,
  },
  sendMessage: {
    path: '/bankng.mucklet.Mucklet/SendMessage',
    requestStream: false,
    responseStream: false,
    requestType: mucklet_pb.SendMessageRequest,
    responseType: google_protobuf_empty_pb.Empty,
    requestSerialize: serialize_bankng_mucklet_SendMessageRequest,
    requestDeserialize: deserialize_bankng_mucklet_SendMessageRequest,
    responseSerialize: serialize_google_protobuf_Empty,
    responseDeserialize: deserialize_google_protobuf_Empty,
  },
};

exports.MuckletClient = grpc.makeGenericClientConstructor(MuckletService);
