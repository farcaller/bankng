package net.dracones.bankng;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.2)",
    comments = "Source: auth.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthGrpc {

  private AuthGrpc() {}

  public static final java.lang.String SERVICE_NAME = "net.dracones.bankng.Auth";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.FirstFactorRequest,
      net.dracones.bankng.FirstFactorReply> getFirstFactorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FirstFactor",
      requestType = net.dracones.bankng.FirstFactorRequest.class,
      responseType = net.dracones.bankng.FirstFactorReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.FirstFactorRequest,
      net.dracones.bankng.FirstFactorReply> getFirstFactorMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.FirstFactorRequest, net.dracones.bankng.FirstFactorReply> getFirstFactorMethod;
    if ((getFirstFactorMethod = AuthGrpc.getFirstFactorMethod) == null) {
      synchronized (AuthGrpc.class) {
        if ((getFirstFactorMethod = AuthGrpc.getFirstFactorMethod) == null) {
          AuthGrpc.getFirstFactorMethod = getFirstFactorMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.FirstFactorRequest, net.dracones.bankng.FirstFactorReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FirstFactor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.FirstFactorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.FirstFactorReply.getDefaultInstance()))
              .setSchemaDescriptor(new AuthMethodDescriptorSupplier("FirstFactor"))
              .build();
        }
      }
    }
    return getFirstFactorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.SecondFactorRequest,
      net.dracones.bankng.SecondFactorReply> getSecondFactorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SecondFactor",
      requestType = net.dracones.bankng.SecondFactorRequest.class,
      responseType = net.dracones.bankng.SecondFactorReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.SecondFactorRequest,
      net.dracones.bankng.SecondFactorReply> getSecondFactorMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.SecondFactorRequest, net.dracones.bankng.SecondFactorReply> getSecondFactorMethod;
    if ((getSecondFactorMethod = AuthGrpc.getSecondFactorMethod) == null) {
      synchronized (AuthGrpc.class) {
        if ((getSecondFactorMethod = AuthGrpc.getSecondFactorMethod) == null) {
          AuthGrpc.getSecondFactorMethod = getSecondFactorMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.SecondFactorRequest, net.dracones.bankng.SecondFactorReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SecondFactor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.SecondFactorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.SecondFactorReply.getDefaultInstance()))
              .setSchemaDescriptor(new AuthMethodDescriptorSupplier("SecondFactor"))
              .build();
        }
      }
    }
    return getSecondFactorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthStub>() {
        @java.lang.Override
        public AuthStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthStub(channel, callOptions);
        }
      };
    return AuthStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthBlockingStub>() {
        @java.lang.Override
        public AuthBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthBlockingStub(channel, callOptions);
        }
      };
    return AuthBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthFutureStub>() {
        @java.lang.Override
        public AuthFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthFutureStub(channel, callOptions);
        }
      };
    return AuthFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void firstFactor(net.dracones.bankng.FirstFactorRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.FirstFactorReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFirstFactorMethod(), responseObserver);
    }

    /**
     */
    default void secondFactor(net.dracones.bankng.SecondFactorRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.SecondFactorReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSecondFactorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Auth.
   */
  public static abstract class AuthImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Auth.
   */
  public static final class AuthStub
      extends io.grpc.stub.AbstractAsyncStub<AuthStub> {
    private AuthStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthStub(channel, callOptions);
    }

    /**
     */
    public void firstFactor(net.dracones.bankng.FirstFactorRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.FirstFactorReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFirstFactorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void secondFactor(net.dracones.bankng.SecondFactorRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.SecondFactorReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSecondFactorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Auth.
   */
  public static final class AuthBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthBlockingStub> {
    private AuthBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.dracones.bankng.FirstFactorReply firstFactor(net.dracones.bankng.FirstFactorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFirstFactorMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.dracones.bankng.SecondFactorReply secondFactor(net.dracones.bankng.SecondFactorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSecondFactorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Auth.
   */
  public static final class AuthFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthFutureStub> {
    private AuthFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.dracones.bankng.FirstFactorReply> firstFactor(
        net.dracones.bankng.FirstFactorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFirstFactorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.dracones.bankng.SecondFactorReply> secondFactor(
        net.dracones.bankng.SecondFactorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSecondFactorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIRST_FACTOR = 0;
  private static final int METHODID_SECOND_FACTOR = 1;

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
        case METHODID_FIRST_FACTOR:
          serviceImpl.firstFactor((net.dracones.bankng.FirstFactorRequest) request,
              (io.grpc.stub.StreamObserver<net.dracones.bankng.FirstFactorReply>) responseObserver);
          break;
        case METHODID_SECOND_FACTOR:
          serviceImpl.secondFactor((net.dracones.bankng.SecondFactorRequest) request,
              (io.grpc.stub.StreamObserver<net.dracones.bankng.SecondFactorReply>) responseObserver);
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
          getFirstFactorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.FirstFactorRequest,
              net.dracones.bankng.FirstFactorReply>(
                service, METHODID_FIRST_FACTOR)))
        .addMethod(
          getSecondFactorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.SecondFactorRequest,
              net.dracones.bankng.SecondFactorReply>(
                service, METHODID_SECOND_FACTOR)))
        .build();
  }

  private static abstract class AuthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return net.dracones.bankng.AuthOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Auth");
    }
  }

  private static final class AuthFileDescriptorSupplier
      extends AuthBaseDescriptorSupplier {
    AuthFileDescriptorSupplier() {}
  }

  private static final class AuthMethodDescriptorSupplier
      extends AuthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuthGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthFileDescriptorSupplier())
              .addMethod(getFirstFactorMethod())
              .addMethod(getSecondFactorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
