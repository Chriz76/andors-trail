package com.gpl.rpg.AndorsTrail.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.gpl.rpg.AndorsTrail.model.actor.Monster;
import com.gpl.rpg.AndorsTrail.util.Coord;

public final class InterfaceData {
	public boolean isMainActivityVisible = false;
	public boolean isInCombat = false;
	public boolean isPlayersCombatTurn = false;
	public Monster selectedMonster;
	public Coord selectedPosition;
	public String selectedTabHeroInfo = "";
	public int selectedQuestFilter = 0; // Should not be parceled

	public int selectedInventoryCategory = 0; //All
	public int selectedInventorySort = 0; //Unsorted
	public int oldSortSelection = 0; // Later will be used for reversing ascending/descending order
	public int selectedSkillCategory = 0; //All
	public int selectedSkillSort = 0; //Unsorted
	public int selectedShopSort = 0; //Unsorted

	public Coord conversationNPC;
	public String conversationPhrase = "";

	public InterfaceData() { }


	// ====== PARCELABLE ===================================================================

	public InterfaceData(DataInputStream src, int fileversion) throws IOException {
		this.isMainActivityVisible = src.readBoolean();
		this.isInCombat = src.readBoolean();
		final boolean hasSelectedPosition = src.readBoolean();
		if (hasSelectedPosition) {
			this.selectedPosition = new Coord(src, fileversion);
		} else {
			this.selectedPosition = null;
		}
		this.selectedTabHeroInfo = src.readUTF();
		this.conversationPhrase = src.readUTF();
		if (src.readBoolean()) {
			this.conversationNPC = new Coord(src, fileversion);
		} else {
			this.conversationNPC = null;
		}
	}

	public void writeToParcel(DataOutputStream dest) throws IOException {
		dest.writeBoolean(isMainActivityVisible);
		dest.writeBoolean(isInCombat);
		if (selectedPosition != null) {
			dest.writeBoolean(true);
			selectedPosition.writeToParcel(dest);
		} else {
			dest.writeBoolean(false);
		}
		dest.writeUTF(selectedTabHeroInfo);
		dest.writeUTF(conversationPhrase);
		if (conversationNPC != null) {
			dest.writeBoolean(true);
			conversationNPC.writeToParcel(dest);
		} else {
			dest.writeBoolean(false);
		}
	}
}
