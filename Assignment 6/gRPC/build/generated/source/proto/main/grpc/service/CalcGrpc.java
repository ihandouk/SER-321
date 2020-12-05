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
    comments = "Source: services/calc.proto")
public final class CalcGrpc {

  private CalcGrpc() {}

  public static final String SERVICE_NAME = "services.Calc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = service.CalcRequest.class,
      responseType = service.CalcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getAddMethod() {
    io.grpc.MethodDescriptor<service.CalcRequest, service.CalcResponse> getAddMethod;
    if ((getAddMethod = CalcGrpc.getAddMethod) == null) {
      synchronized (CalcGrpc.class) {
        if ((getAddMethod = CalcGrpc.getAddMethod) == null) {
          CalcGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<service.CalcRequest, service.CalcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalcMethodDescriptorSupplier("add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getSubtractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subtract",
      requestType = service.CalcRequest.class,
      responseType = service.CalcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getSubtractMethod() {
    io.grpc.MethodDescriptor<service.CalcRequest, service.CalcResponse> getSubtractMethod;
    if ((getSubtractMethod = CalcGrpc.getSubtractMethod) == null) {
      synchronized (CalcGrpc.class) {
        if ((getSubtractMethod = CalcGrpc.getSubtractMethod) == null) {
          CalcGrpc.getSubtractMethod = getSubtractMethod =
              io.grpc.MethodDescriptor.<service.CalcRequest, service.CalcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subtract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalcMethodDescriptorSupplier("subtract"))
              .build();
        }
      }
    }
    return getSubtractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getMultiplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "multiply",
      requestType = service.CalcRequest.class,
      responseType = service.CalcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getMultiplyMethod() {
    io.grpc.MethodDescriptor<service.CalcRequest, service.CalcResponse> getMultiplyMethod;
    if ((getMultiplyMethod = CalcGrpc.getMultiplyMethod) == null) {
      synchronized (CalcGrpc.class) {
        if ((getMultiplyMethod = CalcGrpc.getMultiplyMethod) == null) {
          CalcGrpc.getMultiplyMethod = getMultiplyMethod =
              io.grpc.MethodDescriptor.<service.CalcRequest, service.CalcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "multiply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalcMethodDescriptorSupplier("multiply"))
              .build();
        }
      }
    }
    return getMultiplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getDivideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "divide",
      requestType = service.CalcRequest.class,
      responseType = service.CalcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service.CalcRequest,
      service.CalcResponse> getDivideMethod() {
    io.grpc.MethodDescriptor<service.CalcRequest, service.CalcResponse> getDivideMethod;
    if ((getDivideMethod = CalcGrpc.getDivideMethod) == null) {
      synchronized (CalcGrpc.class) {
        if ((getDivideMethod = CalcGrpc.getDivideMethod) == null) {
          CalcGrpc.getDivideMethod = getDivideMethod =
              io.grpc.MethodDescriptor.<service.CalcRequest, service.CalcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "divide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service.CalcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalcMethodDescriptorSupplier("divide"))
              .build();
        }
      }
    }
    return getDivideMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalcStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalcStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalcStub>() {
        @java.lang.Override
        public CalcStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalcStub(channel, callOptions);
        }
      };
    return CalcStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalcBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalcBlockingStub>() {
        @java.lang.Override
        public CalcBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalcBlockingStub(channel, callOptions);
        }
      };
    return CalcBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalcFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalcFutureStub>() {
        @java.lang.Override
        public CalcFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalcFutureStub(channel, callOptions);
        }
      };
    return CalcFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CalcImplBase implements io.grpc.BindableService {

    /**
     */
    public void add(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    public void subtract(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubtractMethod(), responseObserver);
    }

    /**
     */
    public void multiply(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMultiplyMethod(), responseObserver);
    }

    /**
     */
    public void divide(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDivideMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.CalcRequest,
                service.CalcResponse>(
                  this, METHODID_ADD)))
          .addMethod(
            getSubtractMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.CalcRequest,
                service.CalcResponse>(
                  this, METHODID_SUBTRACT)))
          .addMethod(
            getMultiplyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.CalcRequest,
                service.CalcResponse>(
                  this, METHODID_MULTIPLY)))
          .addMethod(
            getDivideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service.CalcRequest,
                service.CalcResponse>(
                  this, METHODID_DIVIDE)))
          .build();
    }
  }

  /**
   */
  public static final class CalcStub extends io.grpc.stub.AbstractAsyncStub<CalcStub> {
    private CalcStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalcStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalcStub(channel, callOptions);
    }

    /**
     */
    public void add(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subtract(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubtractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void multiply(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMultiplyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void divide(service.CalcRequest request,
        io.grpc.stub.StreamObserver<service.CalcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDivideMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CalcBlockingStub extends io.grpc.stub.AbstractBlockingStub<CalcBlockingStub> {
    private CalcBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalcBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalcBlockingStub(channel, callOptions);
    }

    /**
     */
    public service.CalcResponse add(service.CalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.CalcResponse subtract(service.CalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubtractMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.CalcResponse multiply(service.CalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getMultiplyMethod(), getCallOptions(), request);
    }

    /**
     */
    public service.CalcResponse divide(service.CalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getDivideMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CalcFutureStub extends io.grpc.stub.AbstractFutureStub<CalcFutureStub> {
    private CalcFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalcFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.CalcResponse> add(
        service.CalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.CalcResponse> subtract(
        service.CalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubtractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.CalcResponse> multiply(
        service.CalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMultiplyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<service.CalcResponse> divide(
        service.CalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDivideMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_SUBTRACT = 1;
  private static final int METHODID_MULTIPLY = 2;
  private static final int METHODID_DIVIDE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CalcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CalcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((service.CalcRequest) request,
              (io.grpc.stub.StreamObserver<service.CalcResponse>) responseObserver);
          break;
        case METHODID_SUBTRACT:
          serviceImpl.subtract((service.CalcRequest) request,
              (io.grpc.stub.StreamObserver<service.CalcResponse>) responseObserver);
          break;
        case METHODID_MULTIPLY:
          serviceImpl.multiply((service.CalcRequest) request,
              (io.grpc.stub.StreamObserver<service.CalcResponse>) responseObserver);
          break;
        case METHODID_DIVIDE:
          serviceImpl.divide((service.CalcRequest) request,
              (io.grpc.stub.StreamObserver<service.CalcResponse>) responseObserver);
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

  private static abstract class CalcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service.CalcProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Calc");
    }
  }

  private static final class CalcFileDescriptorSupplier
      extends CalcBaseDescriptorSupplier {
    CalcFileDescriptorSupplier() {}
  }

  private static final class CalcMethodDescriptorSupplier
      extends CalcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalcMethodDescriptorSupplier(String methodName) {
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
      synchronized (CalcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalcFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getSubtractMethod())
              .addMethod(getMultiplyMethod())
              .addMethod(getDivideMethod())
              .build();
        }
      }
    }
    return result;
  }
}
