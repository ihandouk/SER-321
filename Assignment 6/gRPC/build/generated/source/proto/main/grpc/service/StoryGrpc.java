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
    comments = "Source: services/story.proto")
public final class StoryGrpc {

  private StoryGrpc() {}

  public static final String SERVICE_NAME = "services.Story";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service.Empty,
      service.ReadResponse> getReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "read",
      requestType = service.Empty.class,
      responseType = service.ReadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.Empty,
      service.ReadResponse> getReadMethod() {
    io.grpc.MethodDescriptor<service.Empty, service.ReadResponse> getReadMethod;
    if ((getReadMethod = StoryGrpc.getReadMethod) == null) {
      synchronized (StoryGrpc.class) {
        if ((getReadMethod = StoryGrpc.getReadMethod) == null) {
          StoryGrpc.getReadMethod = getReadMethod =
              io.grpc.MethodDescriptor.<service.Empty, service.ReadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "read"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.ReadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StoryMethodDescriptorSupplier("read"))
              .build();
        }
      }
    }
    return getReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.WriteRequest,
      service.WriteResponse> getWriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "write",
      requestType = service.WriteRequest.class,
      responseType = service.WriteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.WriteRequest,
      service.WriteResponse> getWriteMethod() {
    io.grpc.MethodDescriptor<service.WriteRequest, service.WriteResponse> getWriteMethod;
    if ((getWriteMethod = StoryGrpc.getWriteMethod) == null) {
      synchronized (StoryGrpc.class) {
        if ((getWriteMethod = StoryGrpc.getWriteMethod) == null) {
          StoryGrpc.getWriteMethod = getWriteMethod =
              io.grpc.MethodDescriptor.<service.WriteRequest, service.WriteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "write"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.WriteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.WriteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StoryMethodDescriptorSupplier("write"))
              .build();
        }
      }
    }
    return getWriteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StoryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoryStub>() {
        @java.lang.Override
        public StoryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoryStub(channel, callOptions);
        }
      };
    return StoryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StoryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoryBlockingStub>() {
        @java.lang.Override
        public StoryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoryBlockingStub(channel, callOptions);
        }
      };
    return StoryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StoryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoryFutureStub>() {
        @java.lang.Override
        public StoryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoryFutureStub(channel, callOptions);
        }
      };
    return StoryFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StoryImplBase implements io.grpc.BindableService {

    /**
     */
    public void read(service.Empty request,
        io.grpc.stub.StreamObserver<service.ReadResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReadMethod(), responseObserver);
    }

    /**
     */
    public void write(service.WriteRequest request,
        io.grpc.stub.StreamObserver<service.WriteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.Empty,
                service.ReadResponse>(
                  this, METHODID_READ)))
          .addMethod(
            getWriteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.WriteRequest,
                service.WriteResponse>(
                  this, METHODID_WRITE)))
          .build();
    }
  }

  /**
   */
  public static final class StoryStub extends io.grpc.stub.AbstractAsyncStub<StoryStub> {
    private StoryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoryStub(channel, callOptions);
    }

    /**
     */
    public void read(service.Empty request,
        io.grpc.stub.StreamObserver<service.ReadResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void write(service.WriteRequest request,
        io.grpc.stub.StreamObserver<service.WriteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StoryBlockingStub extends io.grpc.stub.AbstractBlockingStub<StoryBlockingStub> {
    private StoryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoryBlockingStub(channel, callOptions);
    }

    /**
     */
    public service.ReadResponse read(service.Empty request) {
      return blockingUnaryCall(
          getChannel(), getReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.WriteResponse write(service.WriteRequest request) {
      return blockingUnaryCall(
          getChannel(), getWriteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StoryFutureStub extends io.grpc.stub.AbstractFutureStub<StoryFutureStub> {
    private StoryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.ReadResponse> read(
        service.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.WriteResponse> write(
        service.WriteRequest request) {
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
    private final StoryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StoryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_READ:
          serviceImpl.read((service.Empty) request,
              (io.grpc.stub.StreamObserver<service.ReadResponse>) responseObserver);
          break;
        case METHODID_WRITE:
          serviceImpl.write((service.WriteRequest) request,
              (io.grpc.stub.StreamObserver<service.WriteResponse>) responseObserver);
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

  private static abstract class StoryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StoryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service.StoryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Story");
    }
  }

  private static final class StoryFileDescriptorSupplier
      extends StoryBaseDescriptorSupplier {
    StoryFileDescriptorSupplier() {}
  }

  private static final class StoryMethodDescriptorSupplier
      extends StoryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StoryMethodDescriptorSupplier(String methodName) {
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
      synchronized (StoryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StoryFileDescriptorSupplier())
              .addMethod(getReadMethod())
              .addMethod(getWriteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
