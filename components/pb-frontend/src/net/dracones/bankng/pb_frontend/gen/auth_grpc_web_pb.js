/**
 * @fileoverview gRPC-Web generated client stub for net.dracones.bankng
 * @enhanceable
 * @public
 */

// Code generated by protoc-gen-grpc-web. DO NOT EDIT.
// versions:
// 	protoc-gen-grpc-web v1.5.0
// 	protoc              v4.24.4
// source: auth.proto


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.net = {};
proto.net.dracones = {};
proto.net.dracones.bankng = require('./auth_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.net.dracones.bankng.AuthClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.net.dracones.bankng.AuthPromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.net.dracones.bankng.FirstFactorRequest,
 *   !proto.net.dracones.bankng.FirstFactorReply>}
 */
const methodDescriptor_Auth_FirstFactor = new grpc.web.MethodDescriptor(
  '/net.dracones.bankng.Auth/FirstFactor',
  grpc.web.MethodType.UNARY,
  proto.net.dracones.bankng.FirstFactorRequest,
  proto.net.dracones.bankng.FirstFactorReply,
  /**
   * @param {!proto.net.dracones.bankng.FirstFactorRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.net.dracones.bankng.FirstFactorReply.deserializeBinary
);


/**
 * @param {!proto.net.dracones.bankng.FirstFactorRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.net.dracones.bankng.FirstFactorReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.net.dracones.bankng.FirstFactorReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.net.dracones.bankng.AuthClient.prototype.firstFactor =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/net.dracones.bankng.Auth/FirstFactor',
      request,
      metadata || {},
      methodDescriptor_Auth_FirstFactor,
      callback);
};


/**
 * @param {!proto.net.dracones.bankng.FirstFactorRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.net.dracones.bankng.FirstFactorReply>}
 *     Promise that resolves to the response
 */
proto.net.dracones.bankng.AuthPromiseClient.prototype.firstFactor =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/net.dracones.bankng.Auth/FirstFactor',
      request,
      metadata || {},
      methodDescriptor_Auth_FirstFactor);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.net.dracones.bankng.SecondFactorRequest,
 *   !proto.net.dracones.bankng.SecondFactorReply>}
 */
const methodDescriptor_Auth_SecondFactor = new grpc.web.MethodDescriptor(
  '/net.dracones.bankng.Auth/SecondFactor',
  grpc.web.MethodType.UNARY,
  proto.net.dracones.bankng.SecondFactorRequest,
  proto.net.dracones.bankng.SecondFactorReply,
  /**
   * @param {!proto.net.dracones.bankng.SecondFactorRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.net.dracones.bankng.SecondFactorReply.deserializeBinary
);


/**
 * @param {!proto.net.dracones.bankng.SecondFactorRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.net.dracones.bankng.SecondFactorReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.net.dracones.bankng.SecondFactorReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.net.dracones.bankng.AuthClient.prototype.secondFactor =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/net.dracones.bankng.Auth/SecondFactor',
      request,
      metadata || {},
      methodDescriptor_Auth_SecondFactor,
      callback);
};


/**
 * @param {!proto.net.dracones.bankng.SecondFactorRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.net.dracones.bankng.SecondFactorReply>}
 *     Promise that resolves to the response
 */
proto.net.dracones.bankng.AuthPromiseClient.prototype.secondFactor =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/net.dracones.bankng.Auth/SecondFactor',
      request,
      metadata || {},
      methodDescriptor_Auth_SecondFactor);
};


module.exports = proto.net.dracones.bankng;
