package com.github.powerlibraries.primitive.collections.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("PMD")
@Getter @AllArgsConstructor
public enum Type {
	GENERIC(false,	"Object",	"<E>",	"E",		"E",			"null") {
		@Override
		public String getArrayType() {
			return "Object";
		}
		
		@Override
		public String getBoxedArrayType() {
			return "Object";
		}
		
		@Override
		public String equal(String a, String b) {
			return "Objects.equals("+a+", "+b+")";
		}
		
		@Override
		public String returnOnInvalidValue(String arg, String returnValue) {
			return "";
		}
		
		@Override
		public String hash(String var) {
			return "Objects.hashCode("+var+")";
		}
	},
	INT(true,		"Int",		"",		"int",		"Integer",		"0"),
	LONG(true,		"Long",		"",		"long",		"Long",			"0L"),
	SHORT(false,	"Short",	"",		"short",	"Short",		"((short)0)"),
	BYTE(false,		"Byte",		"",		"byte",		"Byte",			"0"),
	CHAR(false,		"Char",		"",		"char",		"Character",	"'\\u0000'"),
	BOOLEAN(false,	"Boolean",	"",		"boolean",	"Boolean",		"false"),
	FLOAT(false,	"Float",	"",		"float",	"Float",		"0f"),
	DOUBLE(true,	"Double",	"",		"double",	"Double",		"0d");
	
	private boolean streamSupport;
	private String label;
	private String generic;
	private String type;
	private String boxed;
	private String neutralElement;
	
	public String getArrayType() {
		return type;
	}
	
	public String getBoxedArrayType() {
		return getBoxed();
	}
	
	public String returnOnInvalidValue(String arg, String returnValue) {
		return "if(!("+arg+" instanceof "+boxed+")) {\n\t\t\treturn "+returnValue+";\n\t\t}";
	}
	
	public String equal(String left, String right) {
		return left+" == "+right;
	}
	
	public boolean isPrimitive() {
		return !getBoxed().equals(getType());
	}
	
	public boolean isNumeric() {
		return isPrimitive() && this!=BOOLEAN;
	}
	
	public String getExtendedGeneric() {
		if(getGeneric().isEmpty()) {
			return "";
		}
		else {
			return "<? extends "+getBoxed()+">";
		}
	}
	
	public String hash(String var) {
		return this.boxed+".hashCode("+var+")";
	}
}
