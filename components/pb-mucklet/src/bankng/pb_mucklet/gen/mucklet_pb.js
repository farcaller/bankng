// source: mucklet.proto
/**
 * @fileoverview
 * @enhanceable
 * @suppress {missingRequire} reports error on implicit type usages.
 * @suppress {messageConventions} JS Compiler reports an error if a variable or
 *     field starts with 'MSG_' and isn't a translatable message.
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!
/* eslint-disable */
// @ts-nocheck

var jspb = require('google-protobuf');
var goog = jspb;
var global = (function() {
  if (this) { return this; }
  if (typeof window !== 'undefined') { return window; }
  if (typeof global !== 'undefined') { return global; }
  if (typeof self !== 'undefined') { return self; }
  return Function('return this')();
}.call(null));

var google_protobuf_empty_pb = require('google-protobuf/google/protobuf/empty_pb.js');
goog.object.extend(proto, google_protobuf_empty_pb);
goog.exportSymbol('proto.bankng.mucklet.LookupCharacterReply', null, global);
goog.exportSymbol('proto.bankng.mucklet.LookupCharacterRequest', null, global);
goog.exportSymbol('proto.bankng.mucklet.LookupCharacterRequest.ByCase', null, global);
goog.exportSymbol('proto.bankng.mucklet.SendMessageRequest', null, global);
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.bankng.mucklet.SendMessageRequest = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.bankng.mucklet.SendMessageRequest, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.bankng.mucklet.SendMessageRequest.displayName = 'proto.bankng.mucklet.SendMessageRequest';
}
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.bankng.mucklet.LookupCharacterRequest = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_);
};
goog.inherits(proto.bankng.mucklet.LookupCharacterRequest, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.bankng.mucklet.LookupCharacterRequest.displayName = 'proto.bankng.mucklet.LookupCharacterRequest';
}
/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.bankng.mucklet.LookupCharacterReply = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.bankng.mucklet.LookupCharacterReply, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  /**
   * @public
   * @override
   */
  proto.bankng.mucklet.LookupCharacterReply.displayName = 'proto.bankng.mucklet.LookupCharacterReply';
}



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.bankng.mucklet.SendMessageRequest.prototype.toObject = function(opt_includeInstance) {
  return proto.bankng.mucklet.SendMessageRequest.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.bankng.mucklet.SendMessageRequest} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.SendMessageRequest.toObject = function(includeInstance, msg) {
  var f, obj = {
    charId: jspb.Message.getFieldWithDefault(msg, 1, ""),
    message: jspb.Message.getFieldWithDefault(msg, 2, "")
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.bankng.mucklet.SendMessageRequest}
 */
proto.bankng.mucklet.SendMessageRequest.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.bankng.mucklet.SendMessageRequest;
  return proto.bankng.mucklet.SendMessageRequest.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.bankng.mucklet.SendMessageRequest} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.bankng.mucklet.SendMessageRequest}
 */
proto.bankng.mucklet.SendMessageRequest.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {string} */ (reader.readString());
      msg.setCharId(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setMessage(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.bankng.mucklet.SendMessageRequest.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.bankng.mucklet.SendMessageRequest.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.bankng.mucklet.SendMessageRequest} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.SendMessageRequest.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getCharId();
  if (f.length > 0) {
    writer.writeString(
      1,
      f
    );
  }
  f = message.getMessage();
  if (f.length > 0) {
    writer.writeString(
      2,
      f
    );
  }
};


/**
 * optional string char_id = 1;
 * @return {string}
 */
proto.bankng.mucklet.SendMessageRequest.prototype.getCharId = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.SendMessageRequest} returns this
 */
proto.bankng.mucklet.SendMessageRequest.prototype.setCharId = function(value) {
  return jspb.Message.setProto3StringField(this, 1, value);
};


/**
 * optional string message = 2;
 * @return {string}
 */
proto.bankng.mucklet.SendMessageRequest.prototype.getMessage = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.SendMessageRequest} returns this
 */
proto.bankng.mucklet.SendMessageRequest.prototype.setMessage = function(value) {
  return jspb.Message.setProto3StringField(this, 2, value);
};



/**
 * Oneof group definitions for this message. Each group defines the field
 * numbers belonging to that group. When of these fields' value is set, all
 * other fields in the group are cleared. During deserialization, if multiple
 * fields are encountered for a group, only the last value seen will be kept.
 * @private {!Array<!Array<number>>}
 * @const
 */
proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_ = [[1,2]];

/**
 * @enum {number}
 */
proto.bankng.mucklet.LookupCharacterRequest.ByCase = {
  BY_NOT_SET: 0,
  FULL_NAME: 1,
  CHAR_ID: 2
};

/**
 * @return {proto.bankng.mucklet.LookupCharacterRequest.ByCase}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.getByCase = function() {
  return /** @type {proto.bankng.mucklet.LookupCharacterRequest.ByCase} */(jspb.Message.computeOneofCase(this, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_[0]));
};



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.toObject = function(opt_includeInstance) {
  return proto.bankng.mucklet.LookupCharacterRequest.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.bankng.mucklet.LookupCharacterRequest} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.LookupCharacterRequest.toObject = function(includeInstance, msg) {
  var f, obj = {
    fullName: jspb.Message.getFieldWithDefault(msg, 1, ""),
    charId: jspb.Message.getFieldWithDefault(msg, 2, "")
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.bankng.mucklet.LookupCharacterRequest}
 */
proto.bankng.mucklet.LookupCharacterRequest.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.bankng.mucklet.LookupCharacterRequest;
  return proto.bankng.mucklet.LookupCharacterRequest.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.bankng.mucklet.LookupCharacterRequest} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.bankng.mucklet.LookupCharacterRequest}
 */
proto.bankng.mucklet.LookupCharacterRequest.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {string} */ (reader.readString());
      msg.setFullName(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setCharId(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.bankng.mucklet.LookupCharacterRequest.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.bankng.mucklet.LookupCharacterRequest} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.LookupCharacterRequest.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = /** @type {string} */ (jspb.Message.getField(message, 1));
  if (f != null) {
    writer.writeString(
      1,
      f
    );
  }
  f = /** @type {string} */ (jspb.Message.getField(message, 2));
  if (f != null) {
    writer.writeString(
      2,
      f
    );
  }
};


/**
 * optional string full_name = 1;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.getFullName = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterRequest} returns this
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.setFullName = function(value) {
  return jspb.Message.setOneofField(this, 1, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_[0], value);
};


/**
 * Clears the field making it undefined.
 * @return {!proto.bankng.mucklet.LookupCharacterRequest} returns this
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.clearFullName = function() {
  return jspb.Message.setOneofField(this, 1, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_[0], undefined);
};


/**
 * Returns whether this field is set.
 * @return {boolean}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.hasFullName = function() {
  return jspb.Message.getField(this, 1) != null;
};


/**
 * optional string char_id = 2;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.getCharId = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterRequest} returns this
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.setCharId = function(value) {
  return jspb.Message.setOneofField(this, 2, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_[0], value);
};


/**
 * Clears the field making it undefined.
 * @return {!proto.bankng.mucklet.LookupCharacterRequest} returns this
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.clearCharId = function() {
  return jspb.Message.setOneofField(this, 2, proto.bankng.mucklet.LookupCharacterRequest.oneofGroups_[0], undefined);
};


/**
 * Returns whether this field is set.
 * @return {boolean}
 */
proto.bankng.mucklet.LookupCharacterRequest.prototype.hasCharId = function() {
  return jspb.Message.getField(this, 2) != null;
};





if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * Optional fields that are not set will be set to undefined.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     net/proto2/compiler/js/internal/generator.cc#kKeyword.
 * @param {boolean=} opt_includeInstance Deprecated. whether to include the
 *     JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @return {!Object}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.toObject = function(opt_includeInstance) {
  return proto.bankng.mucklet.LookupCharacterReply.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Deprecated. Whether to include
 *     the JSPB instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.bankng.mucklet.LookupCharacterReply} msg The msg instance to transform.
 * @return {!Object}
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.LookupCharacterReply.toObject = function(includeInstance, msg) {
  var f, obj = {
    firstName: jspb.Message.getFieldWithDefault(msg, 1, ""),
    lastName: jspb.Message.getFieldWithDefault(msg, 2, ""),
    pfpUrl: jspb.Message.getFieldWithDefault(msg, 3, ""),
    charId: jspb.Message.getFieldWithDefault(msg, 4, "")
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.bankng.mucklet.LookupCharacterReply}
 */
proto.bankng.mucklet.LookupCharacterReply.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.bankng.mucklet.LookupCharacterReply;
  return proto.bankng.mucklet.LookupCharacterReply.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.bankng.mucklet.LookupCharacterReply} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.bankng.mucklet.LookupCharacterReply}
 */
proto.bankng.mucklet.LookupCharacterReply.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {string} */ (reader.readString());
      msg.setFirstName(value);
      break;
    case 2:
      var value = /** @type {string} */ (reader.readString());
      msg.setLastName(value);
      break;
    case 3:
      var value = /** @type {string} */ (reader.readString());
      msg.setPfpUrl(value);
      break;
    case 4:
      var value = /** @type {string} */ (reader.readString());
      msg.setCharId(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.bankng.mucklet.LookupCharacterReply.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.bankng.mucklet.LookupCharacterReply} message
 * @param {!jspb.BinaryWriter} writer
 * @suppress {unusedLocalVariables} f is only used for nested messages
 */
proto.bankng.mucklet.LookupCharacterReply.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getFirstName();
  if (f.length > 0) {
    writer.writeString(
      1,
      f
    );
  }
  f = message.getLastName();
  if (f.length > 0) {
    writer.writeString(
      2,
      f
    );
  }
  f = message.getPfpUrl();
  if (f.length > 0) {
    writer.writeString(
      3,
      f
    );
  }
  f = message.getCharId();
  if (f.length > 0) {
    writer.writeString(
      4,
      f
    );
  }
};


/**
 * optional string first_name = 1;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.getFirstName = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 1, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterReply} returns this
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.setFirstName = function(value) {
  return jspb.Message.setProto3StringField(this, 1, value);
};


/**
 * optional string last_name = 2;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.getLastName = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 2, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterReply} returns this
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.setLastName = function(value) {
  return jspb.Message.setProto3StringField(this, 2, value);
};


/**
 * optional string pfp_url = 3;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.getPfpUrl = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 3, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterReply} returns this
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.setPfpUrl = function(value) {
  return jspb.Message.setProto3StringField(this, 3, value);
};


/**
 * optional string char_id = 4;
 * @return {string}
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.getCharId = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 4, ""));
};


/**
 * @param {string} value
 * @return {!proto.bankng.mucklet.LookupCharacterReply} returns this
 */
proto.bankng.mucklet.LookupCharacterReply.prototype.setCharId = function(value) {
  return jspb.Message.setProto3StringField(this, 4, value);
};


goog.object.extend(exports, proto.bankng.mucklet);
