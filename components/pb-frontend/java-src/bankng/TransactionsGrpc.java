package bankng;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: bankng.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TransactionsGrpc {

  private TransactionsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "bankng.Transactions";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bankng.CreateTransactionRequest,
      bankng.CreateTransactionReply> getCreateTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTransaction",
      requestType = bankng.CreateTransactionRequest.class,
      responseType = bankng.CreateTransactionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bankng.CreateTransactionRequest,
      bankng.CreateTransactionReply> getCreateTransactionMethod() {
    io.grpc.MethodDescriptor<bankng.CreateTransactionRequest, bankng.CreateTransactionReply> getCreateTransactionMethod;
    if ((getCreateTransactionMethod = TransactionsGrpc.getCreateTransactionMethod) == null) {
      synchronized (TransactionsGrpc.class) {
        if ((getCreateTransactionMethod = TransactionsGrpc.getCreateTransactionMethod) == null) {
          TransactionsGrpc.getCreateTransactionMethod = getCreateTransactionMethod =
              io.grpc.MethodDescriptor.<bankng.CreateTransactionRequest, bankng.CreateTransactionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bankng.CreateTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bankng.CreateTransactionReply.getDefaultInstance()))
              .setSchemaDescriptor(new TransactionsMethodDescriptorSupplier("CreateTransaction"))
              .build();
        }
      }
    }
    return getCreateTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransactionsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionsStub>() {
        @java.lang.Override
        public TransactionsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionsStub(channel, callOptions);
        }
      };
    return TransactionsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransactionsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionsBlockingStub>() {
        @java.lang.Override
        public TransactionsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionsBlockingStub(channel, callOptions);
        }
      };
    return TransactionsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransactionsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransactionsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransactionsFutureStub>() {
        @java.lang.Override
        public TransactionsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransactionsFutureStub(channel, callOptions);
        }
      };
    return TransactionsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createTransaction(bankng.CreateTransactionRequest request,
        io.grpc.stub.StreamObserver<bankng.CreateTransactionReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTransactionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Transactions.
   */
  public static abstract class TransactionsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TransactionsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Transactions.
   */
  public static final class TransactionsStub
      extends io.grpc.stub.AbstractAsyncStub<TransactionsStub> {
    private TransactionsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionsStub(channel, callOptions);
    }

    /**
     */
    public void createTransaction(bankng.CreateTransactionRequest request,
        io.grpc.stub.StreamObserver<bankng.CreateTransactionReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Transactions.
   */
  public static final class TransactionsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TransactionsBlockingStub> {
    private TransactionsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionsBlockingStub(channel, callOptions);
    }

    /**
     */
    public bankng.CreateTransactionReply createTransaction(bankng.CreateTransactionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTransactionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Transactions.
   */
  public static final class TransactionsFutureStub
      extends io.grpc.stub.AbstractFutureStub<TransactionsFutureStub> {
    private TransactionsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransactionsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransactionsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<bankng.CreateTransactionReply> createTransaction(
        bankng.CreateTransactionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TRANSACTION = 0;

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
        case METHODID_CREATE_TRANSACTION:
          serviceImpl.createTransaction((bankng.CreateTransactionRequest) request,
              (io.grpc.stub.StreamObserver<bankng.CreateTransactionReply>) responseObserver);
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
          getCreateTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              bankng.CreateTransactionRequest,
              bankng.CreateTransactionReply>(
                service, METHODID_CREATE_TRANSACTION)))
        .build();
  }

  private static abstract class TransactionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransactionsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return bankng.Bankng.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Transactions");
    }
  }

  private static final class TransactionsFileDescriptorSupplier
      extends TransactionsBaseDescriptorSupplier {
    TransactionsFileDescriptorSupplier() {}
  }

  private static final class TransactionsMethodDescriptorSupplier
      extends TransactionsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TransactionsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TransactionsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransactionsFileDescriptorSupplier())
              .addMethod(getCreateTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
