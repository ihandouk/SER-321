package service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: services/tips.proto")
public final class TipsGrpc {

  private TipsGrpc() {}

  public static final String SERVICE_NAME = "services.Tips";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service.Empty,
      service.TipsReadResponse> getReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "read",
      requestType = service.Empty.class,
      responseType = service.TipsReadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.Empty,
      service.TipsReadResponse> getReadMethod() {
    io.grpc.MethodDescriptor<service.Empty, service.TipsReadResponse> getReadMethod;
    if ((getReadMethod = TipsGrpc.getReadMethod) == null) {
      synchronized (TipsGrpc.class) {
        if ((getReadMethod = TipsGrpc.getReadMethod) == null) {
          TipsGrpc.getReadMethod = getReadMethod =
              io.grpc.MethodDescriptor.<service.Empty, service.TipsReadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "read"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.TipsReadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TipsMethodDescriptorSupplier("read"))
              .build();
        }
      }
    }
    return getReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.TipsWriteRequest,
      service.TipsWriteResponse> getWriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "write",
      requestType = service.TipsWriteRequest.class,
      responseType = service.TipsWriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.TipsWriteRequest,
      service.TipsWriteResponse> getWriteMethod() {
    io.grpc.MethodDescriptor<service.TipsWriteRequest, service.TipsWriteResponse> getWriteMethod;
    if ((getWriteMethod = TipsGrpc.getWriteMethod) == null) {
      synchronized (TipsGrpc.class) {
        if ((getWriteMethod = TipsGrpc.getWriteMethod) == null) {
          TipsGrpc.getWriteMethod = getWriteMethod =
              io.grpc.MethodDescriptor.<service.TipsWriteRequest, service.TipsWriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "write"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.TipsWriteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.TipsWriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TipsMethodDescriptorSupplier("write"))
              .build();
        }
      }
    }
    return getWriteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TipsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TipsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TipsStub>() {
        @java.lang.Override
        public TipsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TipsStub(channel, callOptions);
        }
      };
    return TipsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TipsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TipsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TipsBlockingStub>() {
        @java.lang.Override
        public TipsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TipsBlockingStub(channel, callOptions);
        }
      };
    return TipsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TipsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TipsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TipsFutureStub>() {
        @java.lang.Override
        public TipsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TipsFutureStub(channel, callOptions);
        }
      };
    return TipsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TipsImplBase implements io.grpc.BindableService {

    /**
     */
    public void read(service.Empty request,
        io.grpc.stub.StreamObserver<service.TipsReadResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReadMethod(), responseObserver);
    }

    /**
     */
    public void write(service.TipsWriteRequest request,
        io.grpc.stub.StreamObserver<service.TipsWriteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.Empty,
                service.TipsReadResponse>(
                  this, METHODID_READ)))
          .addMethod(
            getWriteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.TipsWriteRequest,
                service.TipsWriteResponse>(
                  this, METHODID_WRITE)))
          .build();
    }
  }

  /**
   */
  public static final class TipsStub extends io.grpc.stub.AbstractAsyncStub<TipsStub> {
    private TipsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TipsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TipsStub(channel, callOptions);
    }

    /**
     */
    public void read(service.Empty request,
        io.grpc.stub.StreamObserver<service.TipsReadResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void write(service.TipsWriteRequest request,
        io.grpc.stub.StreamObserver<service.TipsWriteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TipsBlockingStub extends io.grpc.stub.AbstractBlockingStub<TipsBlockingStub> {
    private TipsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TipsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TipsBlockingStub(channel, callOptions);
    }

    /**
     */
    public service.TipsReadResponse read(service.Empty request) {
      return blockingUnaryCall(
          getChannel(), getReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.TipsWriteResponse write(service.TipsWriteRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TipsFutureStub extends io.grpc.stub.AbstractFutureStub<TipsFutureStub> {
    private TipsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TipsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TipsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.TipsReadResponse> read(
        service.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.TipsWriteResponse> write(
        service.TipsWriteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_READ = 0;
  private static final int METHODID_WRITE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TipsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TipsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_READ:
          serviceImpl.read((service.Empty) request,
              (io.grpc.stub.StreamObserver<service.TipsReadResponse>) responseObserver);
          break;
        case METHODID_WRITE:
          serviceImpl.write((service.TipsWriteRequest) request,
              (io.grpc.stub.StreamObserver<service.TipsWriteResponse>) responseObserver);
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

  private static abstract class TipsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TipsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service.TipsProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Tips");
    }
  }

  private static final class TipsFileDescriptorSupplier
      extends TipsBaseDescriptorSupplier {
    TipsFileDescriptorSupplier() {}
  }

  private static final class TipsMethodDescriptorSupplier
      extends TipsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TipsMethodDescriptorSupplier(String methodName) {
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
      synchronized (TipsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TipsFileDescriptorSupplier())
              .addMethod(getReadMethod())
              .addMethod(getWriteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
