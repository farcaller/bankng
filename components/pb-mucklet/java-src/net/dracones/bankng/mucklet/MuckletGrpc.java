package net.dracones.bankng.mucklet;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.2)",
    comments = "Source: mucklet.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MuckletGrpc {

  private MuckletGrpc() {}

  public static final java.lang.String SERVICE_NAME = "net.dracones.bankng.mucklet.Mucklet";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.LookupCharacterRequest,
      net.dracones.bankng.mucklet.LookupCharacterReply> getLookupCharacterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LookupCharacter",
      requestType = net.dracones.bankng.mucklet.LookupCharacterRequest.class,
      responseType = net.dracones.bankng.mucklet.LookupCharacterReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.LookupCharacterRequest,
      net.dracones.bankng.mucklet.LookupCharacterReply> getLookupCharacterMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.LookupCharacterRequest, net.dracones.bankng.mucklet.LookupCharacterReply> getLookupCharacterMethod;
    if ((getLookupCharacterMethod = MuckletGrpc.getLookupCharacterMethod) == null) {
      synchronized (MuckletGrpc.class) {
        if ((getLookupCharacterMethod = MuckletGrpc.getLookupCharacterMethod) == null) {
          MuckletGrpc.getLookupCharacterMethod = getLookupCharacterMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.mucklet.LookupCharacterRequest, net.dracones.bankng.mucklet.LookupCharacterReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LookupCharacter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.mucklet.LookupCharacterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.mucklet.LookupCharacterReply.getDefaultInstance()))
              .setSchemaDescriptor(new MuckletMethodDescriptorSupplier("LookupCharacter"))
              .build();
        }
      }
    }
    return getLookupCharacterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.SendMessageRequest,
      com.google.protobuf.Empty> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = net.dracones.bankng.mucklet.SendMessageRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.SendMessageRequest,
      com.google.protobuf.Empty> getSendMessageMethod() {
    io.grpc.MethodDescriptor<net.dracones.bankng.mucklet.SendMessageRequest, com.google.protobuf.Empty> getSendMessageMethod;
    if ((getSendMessageMethod = MuckletGrpc.getSendMessageMethod) == null) {
      synchronized (MuckletGrpc.class) {
        if ((getSendMessageMethod = MuckletGrpc.getSendMessageMethod) == null) {
          MuckletGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<net.dracones.bankng.mucklet.SendMessageRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.dracones.bankng.mucklet.SendMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MuckletMethodDescriptorSupplier("SendMessage"))
              .build();
        }
      }
    }
    return getSendMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MuckletStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MuckletStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MuckletStub>() {
        @java.lang.Override
        public MuckletStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MuckletStub(channel, callOptions);
        }
      };
    return MuckletStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MuckletBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MuckletBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MuckletBlockingStub>() {
        @java.lang.Override
        public MuckletBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MuckletBlockingStub(channel, callOptions);
        }
      };
    return MuckletBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MuckletFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MuckletFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MuckletFutureStub>() {
        @java.lang.Override
        public MuckletFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MuckletFutureStub(channel, callOptions);
        }
      };
    return MuckletFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void lookupCharacter(net.dracones.bankng.mucklet.LookupCharacterRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.mucklet.LookupCharacterReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLookupCharacterMethod(), responseObserver);
    }

    /**
     */
    default void sendMessage(net.dracones.bankng.mucklet.SendMessageRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Mucklet.
   */
  public static abstract class MuckletImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MuckletGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Mucklet.
   */
  public static final class MuckletStub
      extends io.grpc.stub.AbstractAsyncStub<MuckletStub> {
    private MuckletStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MuckletStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MuckletStub(channel, callOptions);
    }

    /**
     */
    public void lookupCharacter(net.dracones.bankng.mucklet.LookupCharacterRequest request,
        io.grpc.stub.StreamObserver<net.dracones.bankng.mucklet.LookupCharacterReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLookupCharacterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(net.dracones.bankng.mucklet.SendMessageRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Mucklet.
   */
  public static final class MuckletBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MuckletBlockingStub> {
    private MuckletBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MuckletBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MuckletBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.dracones.bankng.mucklet.LookupCharacterReply lookupCharacter(net.dracones.bankng.mucklet.LookupCharacterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLookupCharacterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty sendMessage(net.dracones.bankng.mucklet.SendMessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Mucklet.
   */
  public static final class MuckletFutureStub
      extends io.grpc.stub.AbstractFutureStub<MuckletFutureStub> {
    private MuckletFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MuckletFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MuckletFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.dracones.bankng.mucklet.LookupCharacterReply> lookupCharacter(
        net.dracones.bankng.mucklet.LookupCharacterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLookupCharacterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> sendMessage(
        net.dracones.bankng.mucklet.SendMessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOOKUP_CHARACTER = 0;
  private static final int METHODID_SEND_MESSAGE = 1;

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
        case METHODID_LOOKUP_CHARACTER:
          serviceImpl.lookupCharacter((net.dracones.bankng.mucklet.LookupCharacterRequest) request,
              (io.grpc.stub.StreamObserver<net.dracones.bankng.mucklet.LookupCharacterReply>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((net.dracones.bankng.mucklet.SendMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
          getLookupCharacterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.mucklet.LookupCharacterRequest,
              net.dracones.bankng.mucklet.LookupCharacterReply>(
                service, METHODID_LOOKUP_CHARACTER)))
        .addMethod(
          getSendMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.dracones.bankng.mucklet.SendMessageRequest,
              com.google.protobuf.Empty>(
                service, METHODID_SEND_MESSAGE)))
        .build();
  }

  private static abstract class MuckletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MuckletBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return net.dracones.bankng.mucklet.MuckletOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Mucklet");
    }
  }

  private static final class MuckletFileDescriptorSupplier
      extends MuckletBaseDescriptorSupplier {
    MuckletFileDescriptorSupplier() {}
  }

  private static final class MuckletMethodDescriptorSupplier
      extends MuckletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MuckletMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MuckletGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MuckletFileDescriptorSupplier())
              .addMethod(getLookupCharacterMethod())
              .addMethod(getSendMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
