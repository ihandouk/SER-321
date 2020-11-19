# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: operation.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='operation.proto',
  package='operation',
  syntax='proto2',
  serialized_options=b'\n\007buffersB\017OperationProtos',
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x0foperation.proto\x12\toperation\"\x9f\x02\n\rQuestionneire\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x10\n\x08question\x18\x02 \x01(\t\x12\x0e\n\x06\x61nswer\x18\x03 \x01(\t\x12\x42\n\roperationType\x18\x04 \x01(\x0e\x32&.operation.Questionneire.OperationType:\x03\x41\x44\x44\x12\x41\n\x0cresponseType\x18\x05 \x01(\x0e\x32%.operation.Questionneire.ResponseType:\x04JSON\"3\n\rOperationType\x12\x07\n\x03\x41\x44\x44\x10\x00\x12\x07\n\x03SUB\x10\x01\x12\x07\n\x03MUL\x10\x02\x12\x07\n\x03\x44IV\x10\x03\"$\n\x0cResponseType\x12\x08\n\x04JSON\x10\x00\x12\n\n\x06STRING\x10\x01\x42\x1a\n\x07\x62uffersB\x0fOperationProtos'
)



_QUESTIONNEIRE_OPERATIONTYPE = _descriptor.EnumDescriptor(
  name='OperationType',
  full_name='operation.Questionneire.OperationType',
  filename=None,
  file=DESCRIPTOR,
  create_key=_descriptor._internal_create_key,
  values=[
    _descriptor.EnumValueDescriptor(
      name='ADD', index=0, number=0,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
    _descriptor.EnumValueDescriptor(
      name='SUB', index=1, number=1,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
    _descriptor.EnumValueDescriptor(
      name='MUL', index=2, number=2,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
    _descriptor.EnumValueDescriptor(
      name='DIV', index=3, number=3,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
  ],
  containing_type=None,
  serialized_options=None,
  serialized_start=229,
  serialized_end=280,
)
_sym_db.RegisterEnumDescriptor(_QUESTIONNEIRE_OPERATIONTYPE)

_QUESTIONNEIRE_RESPONSETYPE = _descriptor.EnumDescriptor(
  name='ResponseType',
  full_name='operation.Questionneire.ResponseType',
  filename=None,
  file=DESCRIPTOR,
  create_key=_descriptor._internal_create_key,
  values=[
    _descriptor.EnumValueDescriptor(
      name='JSON', index=0, number=0,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
    _descriptor.EnumValueDescriptor(
      name='STRING', index=1, number=1,
      serialized_options=None,
      type=None,
      create_key=_descriptor._internal_create_key),
  ],
  containing_type=None,
  serialized_options=None,
  serialized_start=282,
  serialized_end=318,
)
_sym_db.RegisterEnumDescriptor(_QUESTIONNEIRE_RESPONSETYPE)


_QUESTIONNEIRE = _descriptor.Descriptor(
  name='Questionneire',
  full_name='operation.Questionneire',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='operation.Questionneire.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='question', full_name='operation.Questionneire.question', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='answer', full_name='operation.Questionneire.answer', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='operationType', full_name='operation.Questionneire.operationType', index=3,
      number=4, type=14, cpp_type=8, label=1,
      has_default_value=True, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='responseType', full_name='operation.Questionneire.responseType', index=4,
      number=5, type=14, cpp_type=8, label=1,
      has_default_value=True, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
    _QUESTIONNEIRE_OPERATIONTYPE,
    _QUESTIONNEIRE_RESPONSETYPE,
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto2',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=31,
  serialized_end=318,
)

_QUESTIONNEIRE.fields_by_name['operationType'].enum_type = _QUESTIONNEIRE_OPERATIONTYPE
_QUESTIONNEIRE.fields_by_name['responseType'].enum_type = _QUESTIONNEIRE_RESPONSETYPE
_QUESTIONNEIRE_OPERATIONTYPE.containing_type = _QUESTIONNEIRE
_QUESTIONNEIRE_RESPONSETYPE.containing_type = _QUESTIONNEIRE
DESCRIPTOR.message_types_by_name['Questionneire'] = _QUESTIONNEIRE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Questionneire = _reflection.GeneratedProtocolMessageType('Questionneire', (_message.Message,), {
  'DESCRIPTOR' : _QUESTIONNEIRE,
  '__module__' : 'operation_pb2'
  # @@protoc_insertion_point(class_scope:operation.Questionneire)
  })
_sym_db.RegisterMessage(Questionneire)


DESCRIPTOR._options = None
# @@protoc_insertion_point(module_scope)