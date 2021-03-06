package com.massivecraft.massivecore.cmd;

public class CmdMassiveCoreBuffer extends MassiveCoreCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdMassiveCoreBuffer i = new CmdMassiveCoreBuffer();
	public static CmdMassiveCoreBuffer get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdMassiveCoreBufferPrint cmdMassiveCoreBufferPrint = new CmdMassiveCoreBufferPrint();
	public CmdMassiveCoreBufferClear cmdMassiveCoreBufferClear = new CmdMassiveCoreBufferClear();
	public CmdMassiveCoreBufferSet cmdMassiveCoreBufferSet = new CmdMassiveCoreBufferSet();
	public CmdMassiveCoreBufferAdd cmdMassiveCoreBufferAdd = new CmdMassiveCoreBufferAdd();
	public CmdMassiveCoreBufferWhitespace cmdMassiveCoreBufferWhitespace = new CmdMassiveCoreBufferWhitespace();
	
}
