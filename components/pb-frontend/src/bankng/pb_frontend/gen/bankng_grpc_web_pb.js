/**
 * @fileoverview gRPC-Web generated client stub for bankng
 * @enhanceable
 * @public
 */

// Code generated by protoc-gen-grpc-web. DO NOT EDIT.
// versions:
// 	protoc-gen-grpc-web v1.5.0
// 	protoc              v4.24.4
// source: bankng.proto


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');


var google_protobuf_timestamp_pb = require('google-protobuf/google/protobuf/timestamp_pb.js')

var google_protobuf_descriptor_pb = require('google-protobuf/google/protobuf/descriptor_pb.js')
const proto = {};
proto.bankng = require('./bankng_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.bankng.AccountsClient =
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
proto.bankng.AccountsPromiseClient =
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
 *   !proto.bankng.ListAccountsRequest,
 *   !proto.bankng.ListAccountsReply>}
 */
const methodDescriptor_Accounts_ListAccounts = new grpc.web.MethodDescriptor(
  '/bankng.Accounts/ListAccounts',
  grpc.web.MethodType.UNARY,
  proto.bankng.ListAccountsRequest,
  proto.bankng.ListAccountsReply,
  /**
   * @param {!proto.bankng.ListAccountsRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.bankng.ListAccountsReply.deserializeBinary
);


/**
 * @param {!proto.bankng.ListAccountsRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.bankng.ListAccountsReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.bankng.ListAccountsReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.bankng.AccountsClient.prototype.listAccounts =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/bankng.Accounts/ListAccounts',
      request,
      metadata || {},
      methodDescriptor_Accounts_ListAccounts,
      callback);
};


/**
 * @param {!proto.bankng.ListAccountsRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.bankng.ListAccountsReply>}
 *     Promise that resolves to the response
 */
proto.bankng.AccountsPromiseClient.prototype.listAccounts =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/bankng.Accounts/ListAccounts',
      request,
      metadata || {},
      methodDescriptor_Accounts_ListAccounts);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.bankng.ListTransactionsRequest,
 *   !proto.bankng.ListTransactionsReply>}
 */
const methodDescriptor_Accounts_ListTransactions = new grpc.web.MethodDescriptor(
  '/bankng.Accounts/ListTransactions',
  grpc.web.MethodType.UNARY,
  proto.bankng.ListTransactionsRequest,
  proto.bankng.ListTransactionsReply,
  /**
   * @param {!proto.bankng.ListTransactionsRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.bankng.ListTransactionsReply.deserializeBinary
);


/**
 * @param {!proto.bankng.ListTransactionsRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.bankng.ListTransactionsReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.bankng.ListTransactionsReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.bankng.AccountsClient.prototype.listTransactions =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/bankng.Accounts/ListTransactions',
      request,
      metadata || {},
      methodDescriptor_Accounts_ListTransactions,
      callback);
};


/**
 * @param {!proto.bankng.ListTransactionsRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.bankng.ListTransactionsReply>}
 *     Promise that resolves to the response
 */
proto.bankng.AccountsPromiseClient.prototype.listTransactions =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/bankng.Accounts/ListTransactions',
      request,
      metadata || {},
      methodDescriptor_Accounts_ListTransactions);
};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.bankng.TransactionsClient =
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
proto.bankng.TransactionsPromiseClient =
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
 *   !proto.bankng.CreateTransactionRequest,
 *   !proto.bankng.CreateTransactionReply>}
 */
const methodDescriptor_Transactions_CreateTransaction = new grpc.web.MethodDescriptor(
  '/bankng.Transactions/CreateTransaction',
  grpc.web.MethodType.UNARY,
  proto.bankng.CreateTransactionRequest,
  proto.bankng.CreateTransactionReply,
  /**
   * @param {!proto.bankng.CreateTransactionRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.bankng.CreateTransactionReply.deserializeBinary
);


/**
 * @param {!proto.bankng.CreateTransactionRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.bankng.CreateTransactionReply)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.bankng.CreateTransactionReply>|undefined}
 *     The XHR Node Readable Stream
 */
proto.bankng.TransactionsClient.prototype.createTransaction =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/bankng.Transactions/CreateTransaction',
      request,
      metadata || {},
      methodDescriptor_Transactions_CreateTransaction,
      callback);
};


/**
 * @param {!proto.bankng.CreateTransactionRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.bankng.CreateTransactionReply>}
 *     Promise that resolves to the response
 */
proto.bankng.TransactionsPromiseClient.prototype.createTransaction =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/bankng.Transactions/CreateTransaction',
      request,
      metadata || {},
      methodDescriptor_Transactions_CreateTransaction);
};


module.exports = proto.bankng;

