
/* $Id$
 *
 * Revisions:
 *   $Log: Lane.java,v $
 *   Revision 1.52  2003/02/20 20:27:45  ???
 *   Fouls disables.
 *
 *   Revision 1.51  2003/02/20 20:01:32  ???
 *   Added things.
 *
 *   Revision 1.50  2003/02/20 19:53:52  ???
 *   Added foul support.  Still need to update laneview and test this.
 *
 *   Revision 1.49  2003/02/20 11:18:22  ???
 *   Works beautifully.
 *
 *   Revision 1.48  2003/02/20 04:10:58  ???
 *   Score reporting code should be good.
 *
 *   Revision 1.47  2003/02/17 00:25:28  ???
 *   Added disbale controls for View objects.
 *
 *   Revision 1.46  2003/02/17 00:20:47  ???
 *   fix for event when game ends
 *
 *   Revision 1.43  2003/02/17 00:09:42  ???
 *   fix for event when game ends
 *
 *   Revision 1.42  2003/02/17 00:03:34  ???
 *   Bug fixed
 *
 *   Revision 1.41  2003/02/16 23:59:49  ???
 *   Reporting of sorts.
 *
 *   Revision 1.40  2003/02/16 23:44:33  ???
 *   added mechnanical problem flag
 *
 *   Revision 1.39  2003/02/16 23:43:08  ???
 *   added mechnanical problem flag
 *
 *   Revision 1.38  2003/02/16 23:41:05  ???
 *   added mechnanical problem flag
 *
 *   Revision 1.37  2003/02/16 23:00:26  ???
 *   added mechnanical problem flag
 *
 *   Revision 1.36  2003/02/16 21:31:04  ???
 *   Score logging.
 *
 *   Revision 1.35  2003/02/09 21:38:00  ???
 *   Added lots of comments
 *
 *   Revision 1.34  2003/02/06 00:27:46  ???
 *   Fixed a race condition
 *
 *   Revision 1.33  2003/02/05 11:16:34  ???
 *   Boom-Shacka-Lacka!!!
 *
 *   Revision 1.32  2003/02/05 01:15:19  ???
 *   Real close now.  Honest.
 *
 *   Revision 1.31  2003/02/04 22:02:04  ???
 *   Still not quite working...
 *
 *   Revision 1.30  2003/02/04 13:33:04  ???
 *   Lane may very well work now.
 *
 *   Revision 1.29  2003/02/02 23:57:27  ???
 *   fix on pinsetter hack
 *
 *   Revision 1.28  2003/02/02 23:49:48  ???
 *   Pinsetter generates an event when all pins are reset
 *
 *   Revision 1.27  2003/02/02 23:26:32  ???
 *   ControlDesk now runs its own thread and polls for free lanes to assign queue members to
 *
 *   Revision 1.26  2003/02/02 23:11:42  ???
 *   parties can now play more than 1 game on a lane, and lanes are properly released after games
 *
 *   Revision 1.25  2003/02/02 22:52:19  ???
 *   Lane compiles
 *
 *   Revision 1.24  2003/02/02 22:50:10  ???
 *   Lane compiles
 *
 *   Revision 1.23  2003/02/02 22:47:34  ???
 *   More observering.
 *
 *   Revision 1.22  2003/02/02 22:15:40  ???
 *   Add accessor for pinsetter.
 *
 *   Revision 1.21  2003/02/02 21:59:20  ???
 *   added conditions for the party choosing to play another game
 *
 *   Revision 1.20  2003/02/02 21:51:54  ???
 *   LaneEvent may very well be observer method.
 *
 *   Revision 1.19  2003/02/02 20:28:59  ???
 *   fixed sleep thread bug in lane
 *
 *   Revision 1.18  2003/02/02 18:18:51  ???
 *   more changes. just need to fix scoring.
 *
 *   Revision 1.17  2003/02/02 17:47:02  ???
 *   Things are pretty close to working now...
 *
 *   Revision 1.16  2003/01/30 22:09:32  ???
 *   Worked on scoring.
 *
 *   Revision 1.15  2003/01/30 21:45:08  ???
 *   Fixed speling of received in Lane.
 *
 *   Revision 1.14  2003/01/30 21:29:30  ???
 *   Fixed some MVC stuff
 *
 *   Revision 1.13  2003/01/30 03:45:26  ???
 *   *** empty log message ***
 *
 *   Revision 1.12  2003/01/26 23:16:10  ???
 *   Improved thread handeling in lane/controldesk
 *
 *   Revision 1.11  2003/01/26 22:34:44  ???
 *   Total rewrite of lane and pinsetter for R2's observer model
 *   Added Lane/Pinsetter Observer
 *   Rewrite of scoring algorythm in lane
 *
 *   Revision 1.10  2003/01/26 20:44:05  ???
 *   small changes
 *
 * 
 */

import java.util.*;
import java.util.Iterator;

import State.BowlingFrame;
import State.ScoreCalculationState;

public class Lane extends Thread implements PinsetterObserver {
	public Party party;
	public Pinsetter setter;
	private HashMap scores;
	private Vector subscribers;

	private boolean partyAssigned;
	private GameState gameState;
	private Iterator bowlerIterator;
	private int ball;
	public int bowlIndex;
	public int frameNumber;
	public boolean tenthFrameStrike;

	private int[] curScores;
	public int[][] cumulScores;
	public boolean canThrowAgain;

	public int[][] finalScores;
	public int gameNumber;

	private Bowler currentThrower; // = the thrower who just took a throw

	private Game currentGame;

	/**
	 * Lane()
	 * <p>
	 * Constructs a new lane and starts its thread
	 *
	 * @pre none
	 * @post a new lane has been created and its thered is executing
	 */
	public Lane() {
		setter = new Pinsetter();
		scores = new HashMap();
		subscribers = new Vector();

		partyAssigned = false;

		gameNumber = 0;

		setter.subscribe(this);

		this.start();
	}

	/**
	 * run()
	 * <p>
	 * entry point for execution of this lane
	 */
	public void run() {

		while (true) {
			try {
				synchronized (this) {
					//Thread will wait till a game is started
					wait();
				}
			} catch (InterruptedException e) {
				System.out.println("Party has been assigned, Running Lane...");
			}
			currentGame = new Game(this);

			try {
				currentGame.begin();
			} catch (Exception e) {
				e.printStackTrace();
			}

			while (currentGame.hasNextTurn()) {
				currentGame.nextTurn();
			}
			currentGame.end();
		}
	}

	/**
	 * recievePinsetterEvent()
	 * <p>
	 * recieves the thrown event from the pinsetter
	 *
	 * @param pe The pinsetter event that has been received.
	 * @pre none
	 * @post the event has been acted upon if desiered
	 */
	public void receivePinsetterEvent(PinsetterEvent pe) {

		if (pe.pinsDownOnThisThrow() >= 0) {            // this is a real throw
			markScore(currentGame.getCurrentThrower(), frameNumber + 1, pe.getThrowNumber(), pe.pinsDownOnThisThrow());

			// next logic handles the ?: what conditions dont allow them another throw?
			// handle the case of 10th frame first
			if (frameNumber == 9) {
				if (pe.totalPinsDown() == 10) {
					setter.resetPins();
					if (pe.getThrowNumber() == 1) {
						tenthFrameStrike = true;
					}
				}

				if ((pe.totalPinsDown() != 10) && (pe.getThrowNumber() == 2 && tenthFrameStrike == false)) {
					canThrowAgain = false;
					//publish( lanePublish() );
				}

				if (pe.getThrowNumber() == 3) {
					canThrowAgain = false;
					//publish( lanePublish() );
				}
			} else { // its not the 10th frame

				if (pe.pinsDownOnThisThrow() == 10) {        // threw a strike
					canThrowAgain = false;
					//publish( lanePublish() );
				} else if (pe.getThrowNumber() == 2) {
					canThrowAgain = false;
					//publish( lanePublish() );
				} else if (pe.getThrowNumber() == 3)
					System.out.println("I'm here...");
			}
		} else {                                //  this is not a real throw, probably a reset
		}
	}


	/**
	 * resetScores()
	 * <p>
	 * resets the scoring mechanism, must be called before scoring starts
	 *
	 * @pre the party has been assigned
	 * @post scoring system is initialized
	 */
	public void resetScores() {
		Iterator bowlIt = (party.getMembers()).iterator();

		while (bowlIt.hasNext()) {
			int[] toPut = new int[25];
			for (int i = 0; i != 25; i++) {
				toPut[i] = -1;
			}
			scores.put(bowlIt.next(), toPut);
		}

		frameNumber = 0;
	}

	/**
	 * assignParty()
	 * <p>
	 * assigns a party to this lane
	 *
	 * @param theParty Party to be assigned
	 * @pre none
	 * @post the party has been assigned to the lane
	 */
	public void assignParty(Party theParty) {
		party = theParty;

		curScores = new int[party.getMembers().size()];
		cumulScores = new int[party.getMembers().size()][10];
		finalScores = new int[party.getMembers().size()][128]; //Hardcoding a max of 128 games, bite me.
		gameNumber = 0;

		resetScores();

		this.interrupt(); //Party assigned so interrupt the thread
	}

	/**
	 * markScore()
	 * <p>
	 * Method that marks a bowlers score on the board.
	 *
	 * @param Cur   The current bowler
	 * @param frame The frame that bowler is on
	 * @param ball  The ball the bowler is on
	 * @param score The bowler's score
	 */
	private void markScore(Bowler Cur, int frame, int ball, int score) {
		int[] curScore;
		int index = ((frame - 1) * 2 + ball);

		curScore = (int[]) scores.get(Cur);


		curScore[index - 1] = score;
		scores.put(Cur, curScore);
		getScore(Cur, frame);
		publish(lanePublish());
	}

	/**
	 * lanePublish()
	 * <p>
	 * Method that creates and returns a newly created laneEvent
	 *
	 * @return The new lane event
	 */
	public LaneEvent lanePublish() {
		LaneEvent laneEvent = new LaneEvent(
				party,
				bowlIndex,
				currentGame.getCurrentThrower(),
				cumulScores,
				scores,
				frameNumber + 1,
				curScores,
				currentGame.getBall(),
				!(currentGame.getState() instanceof HaltedGameState)
		);
		return laneEvent;
	}

	/**
	 * getScore()
	 * <p>
	 * Method that calculates a bowlers score
	 *
	 * @param bowler The bowler that is currently up
	 * @param frame
	 * @return The bowlers total score
	 */
	private int getScore(Bowler bowler, int frame) {
		int[] Scores = (int[]) scores.get(bowler);
		ArrayList<BowlingFrame> frames = formatScores(Scores);
		ScoreCalculationState context = new ScoreCalculationState(frames);
		int currTotal = context.calculateTotal();

		int total = 0;
		for (int i = 0; i < frames.size(); i++) {
			total += frames.get(i).getFrameScore();
			cumulScores[bowlIndex][i] = total;
		}
		return currTotal;
	}

	/**
	 * converts the array of scores into an arraylist of Bowling frames with the correct scores
	 *
	 * @param scores the list of scores to be formated
	 * @return an ArrayList of frames
	 */
	public static ArrayList<BowlingFrame> formatScores(int[] scores) {
		ArrayList<BowlingFrame> frames = new ArrayList<>();

		boolean createFrame = true;
		BowlingFrame newFrame = new BowlingFrame(false);

		for (int i = 0; i < 21; i++) {
			int score = scores[i];

			if (createFrame) {
				if (i < 18) {
					newFrame = new BowlingFrame(false);
				} else {
					newFrame = new BowlingFrame(true);
				}
				try {
					newFrame.addRoll(score);
				} catch (BowlingFrame.FrameException e) {
					e.printStackTrace();
				}

				createFrame = false;
			} else {
				try {
					newFrame.addRoll(score);

					if (i < 20) {
						frames.add(newFrame);
					}
				} catch (BowlingFrame.FrameException e) {
					e.printStackTrace();
				}
				if (i < 18) {
					createFrame = true;
				}
			}
		}
		return frames;
	}

	/**
	 * isPartyAssigned()
	 * <p>
	 * checks if a party is assigned to this lane
	 *
	 * @return true if party assigned, false otherwise
	 */
	public boolean isPartyAssigned() {
		return party != null;
	}

	/**
	 * subscribe
	 * <p>
	 * Method that will add a subscriber
	 *
	 * @param adding Observer that is to be added
	 */

	public void subscribe(LaneObserver adding) {
		subscribers.add(adding);
	}

	/**
	 * unsubscribe
	 * <p>
	 * Method that unsubscribes an observer from this object
	 *
	 * @param removing The observer to be removed
	 */

	public void unsubscribe(LaneObserver removing) {
		subscribers.remove(removing);
	}

	/**
	 * publish
	 * <p>
	 * Method that publishes an event to subscribers
	 *
	 * @param event Event that is to be published
	 */

	public void publish(LaneEvent event) {
		if (subscribers.size() > 0) {
			Iterator eventIterator = (Iterator) subscribers.iterator();

			while (eventIterator.hasNext()) {
				((LaneObserver) eventIterator.next()).receiveLaneEvent(event);
			}
		}
	}

	/**
	 * Accessor to get this Lane's pinsetter
	 *
	 * @return A reference to this lane's pinsetter
	 */

	public Pinsetter getPinsetter() {
		return setter;
	}

	/**
	 * Pause the execution of this game
	 */
	public void pauseGame() {
		currentGame.pause();
		publish(lanePublish());
	}

	/**
	 * Resume the execution of this game
	 */
	public void unPauseGame() {
		try {
			currentGame.unpause();
		} catch (Exception e) {
			e.printStackTrace();
		}

		publish(lanePublish());
	}

}

