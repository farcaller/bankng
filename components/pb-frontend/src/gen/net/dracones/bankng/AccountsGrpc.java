package net.dracones.bankng;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.1)",
    comments = "Source: accounts.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountsGrpc {

  private AccountsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "net.dracones.bankng.Accounts";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.ListAccountsRequest,
      net.dracones.bankng.ListAccountsResponse> getListAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListAccounts",
      requestType = net.dracones.bankng.ListAccountsRequest.class,
      responseType = net.dracones.bankng.ListAccountsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.ListAccountsRequest,
      net.dracones.bankng.ListAccountsResponse> getListAccountsMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.ListAccountsRequest, net.dracones.bankng.ListAccountsResponse> getListAccountsMethod;
    if ((getListAccountsMethod = AccountsGrpc.getListAccountsMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getListAccountsMethod = AccountsGrpc.getListAccountsMethod) == null) {
          AccountsGrpc.getListAccountsMethod = getListAccountsMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.ListAccountsRequest, net.dracones.bankng.ListAccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.ListAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.ListAccountsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ListAccounts"))
              .build();
        }
      }
    }
    return getListAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.ListTransactionsRequest,
      net.dracones.bankng.ListTransactionsResponse> getListTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTransactions",
      requestType = net.dracones.bankng.ListTransactionsRequest.class,
      responseType = net.dracones.bankng.ListTransactionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.ListTransactionsRequest,
      net.dracones.bankng.ListTransactionsResponse> getListTransactionsMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.ListTransactionsRequest, net.dracones.bankng.ListTransactionsResponse> getListTransactionsMethod;
    if ((getListTransactionsMethod = AccountsGrpc.getListTransactionsMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getListTransactionsMethod = AccountsGrpc.getListTransactionsMethod) == null) {
          AccountsGrpc.getListTransactionsMethod = getListTransactionsMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.ListTransactionsRequest, net.dracones.bankng.ListTransactionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.ListTransactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.ListTransactionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ListTransactions"))
              .build();
        }
      }
    }
    return getListTransactionsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsStub>() {
        @java.lang.Override
        public AccountsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsStub(channel, callOptions);
        }
      };
    return AccountsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsBlockingStub>() {
        @java.lang.Override
        public AccountsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsBlockingStub(channel, callOptions);
        }
      };
    return AccountsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsFutureStub>() {
        @java.lang.Override
        public AccountsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsFutureStub(channel, callOptions);
        }
      };
    return AccountsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void listAccounts(net.dracones.bankng.ListAccountsRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.ListAccountsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAccountsMethod(), responseObserver);
    }

    /**
     */
    default void listTransactions(net.dracones.bankng.ListTransactionsRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.ListTransactionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTransactionsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Accounts.
   */
  public static abstract class AccountsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AccountsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Accounts.
   */
  public static final class AccountsStub
      extends io.grpc.stub.AbstractAsyncStub<AccountsStub> {
    private AccountsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsStub(channel, callOptions);
    }

    /**
     */
    public void listAccounts(net.dracones.bankng.ListAccountsRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.ListAccountsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listTransactions(net.dracones.bankng.ListTransactionsRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.ListTransactionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListTransactionsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Accounts.
   */
  public static final class AccountsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountsBlockingStub> {
    private AccountsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.dracones.bankng.ListAccountsResponse listAccounts(net.dracones.bankng.ListAccountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAccountsMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.dracones.bankng.ListTransactionsResponse listTransactions(net.dracones.bankng.ListTransactionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTransactionsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Accounts.
   */
  public static final class AccountsFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountsFutureStub> {
    private AccountsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.dracones.bankng.ListAccountsResponse> listAccounts(
        net.dracones.bankng.ListAccountsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAccountsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.dracones.bankng.ListTransactionsResponse> listTransactions(
        net.dracones.bankng.ListTransactionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListTransactionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_ACCOUNTS = 0;
  private static final int METHODID_LIST_TRANSACTIONS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_ACCOUNTS:
          serviceImpl.listAccounts((net.dracones.bankng.ListAccountsRequest) request,
              (io.grpc.stub.StreamObserver<net.dracones.bankng.ListAccountsResponse>) responseObserver);
          break;
        case METHODID_LIST_TRANSACTIONS:
          serviceImpl.listTransactions((net.dracones.bankng.ListTransactionsRequest) request,
              (io.grpc.stub.StreamObserver<net.dracones.bankng.ListTransactionsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getListAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.ListAccountsRequest,
              net.dracones.bankng.ListAccountsResponse>(
                service, METHODID_LIST_ACCOUNTS)))
        .addMethod(
          getListTransactionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.ListTransactionsRequest,
              net.dracones.bankng.ListTransactionsResponse>(
                service, METHODID_LIST_TRANSACTIONS)))
        .build();
  }

  private static abstract class AccountsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return net.dracones.bankng.AccountsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Accounts");
    }
  }

  private static final class AccountsFileDescriptorSupplier
      extends AccountsBaseDescriptorSupplier {
    AccountsFileDescriptorSupplier() {}
  }

  private static final class AccountsMethodDescriptorSupplier
      extends AccountsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AccountsMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AccountsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountsFileDescriptorSupplier())
              .addMethod(getListAccountsMethod())
              .addMethod(getListTransactionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
