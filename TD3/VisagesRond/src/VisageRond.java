import java.awt.Graphics;

/**
 * Cette classe permet de modeliser un visage de forme ovale.
 * <p>
 * Le repere graphique est defini avec son origine en haut a gauche de la zône
 * de dessin, l'axe des x horizontal et l'axe des y vertical vers le bas.
 * <p>
 * Un visage est defini par :
 * <ul>
 * <li> les coordonnees xhg, yhg du coin superieur gauche du rectangle
 * englobant,</li>
 * <li> une largeur et une hauteur,</li>
 * <li> un deplacement elementaire horizontal (dx) et un deplacement elementaire
 * vertical (dy).</li>
 * </ul>
 * 
 * <center><img SRC="figurevisage.gif" NOSAVE height=266 width=300
 * align=ABSCENTER></center>
 * 
 * @author Philippe Genoud
 * @version derniere modification 27/09/99
 * 
 */

public class VisageRond {

	// ---------------------------------------------------------
	// Les constantes de la classe VisageRond
	// ---------------------------------------------------------

	/**
	 * Largeur minimale pour un VisageRond.
	 */
	public static final int LARGEUR_MIN = 15;

	/**
	 * Hauteur minimale pour un VisageRond.
	 */
	public static final int HAUTEUR_MIN = 15;

	// -------------------------------------------------------------
	// Les attributs (variables d'instance) de la classe VisageRond
	// -------------------------------------------------------------

	/**
	 * La zône de dessin dans laquelle se trouve le VisageRond.
	 */
	private Dessin d;

	/**
	 * abscisse coin superieur gauche du rectangle englobant le visage.
	 */
	private int xhg = 0;

	/**
	 * ordonnee coin superieur gauche du rectangle englobant le visage.
	 */
	private int yhg = 0;

	/**
	 * largeur du visage. Par defaut 50 pixels.
	 */
	private int largeur = 50;

	/**
	 * hauteur du visage. Par defaut 50 pixels.
	 */
	private int hauteur = 50;

	/**
	 * deplacement elementaire horizontal du visage. Par defaut 5 pixels.
	 */
	private int dx = 5;

	/**
	 * deplacement elementaire vertical du visage. Par defaut 5 pixels.
	 */
	private int dy = 5;

	// ---------------------------------------------------------
	// Les constructeurs de la classe VisageRond
	// ---------------------------------------------------------

	/**
	 * Constructeur avec valeurs par defaut. Cree un visage de taille 50x50 dont
	 * le coin superieur gauche du rectangle englobant est situe au point (0,0)
	 * de la zône de dessin. Ce visage est egalement dote d'un deplacement
	 * elementaire horizontal et vertical de +5 pixels.
	 */
	public VisageRond() {
	}

	/**
	 * Constructeur avec positionnement du visage. Cree un visage de taille
	 * 50x50 mais dont la position du coin superieur gauche du rectangle
	 * englobant est fixee a la creation. Ce visage est dote d'un deplacement
	 * elementaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin superieur gauche du rectangle englobant.
	 * @param yg
	 *            ordonnee du coin superieur gauche du rectangle englobant.
	 */
	public VisageRond(int xg, int yg) {
		xhg = xg;
		yhg = yg;

	}

	/**
	 * Constructeur avec positionnement du visage et definition de sa taille.
	 * Cree un visage dont les diemensions et la position du coin superieur
	 * gauche du rectangle englobant sont fixees a la creation. Ce visage est
	 * dote d'un deplacement elementaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin superieur gauche du rectangle englobant.
	 * @param yg
	 *            abscisse du coin superieur gauche du rectangle englobant.
	 * @param larg
	 *            largeur du visage. La largeur doit etre superieure a
	 *            LARGEUR_MIN
	 * @param larg
	 *            hauteur du visage. La hauteur doit etre superieure a
	 *            HAUTEUR_MIN
	 * 
	 * @see VisageRond#LARGEUR_MIN
	 * @see VisageRond#HAUTEUR_MIN
	 */
	public VisageRond(int xg, int yg, int larg, int haut) {
		xhg = xg;
		yhg = yg;

		largeur = Math.max(larg, LARGEUR_MIN);
		hauteur = Math.max(haut, HAUTEUR_MIN);
	}

	/**
	 * Donne la valeur du deplacement elementaire horizontal.
	 * 
	 * @return valeur de dx, deplacement elementaire horizontal.
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * Fixe deplacement elementaire horizontal.
	 * 
	 * @param v
	 *            Valeur a affecter a dx, deplacement elementaire horizontal.
	 */
	public void setDx(int v) {
		this.dx = v;
	}
        /**
         * Donne la valeur du deplacement elementaire vertical
         * @return valeur dy, deplacement elementaire vertical
         */
	public int getDy() {
		return dy;
	}
        /**
         * Fixe le deplacement elementaire vertical.
         * @param v
         *          Valeur a affecter a dy, deplacement elementaire vertical.
         */        
	public void setDy(int v) {
		this.dy = v;
	}

	/**
	 * Inverse sens du deplacement horizontal.
	 */
	public void inverserDx() {
		dx = -dx;
	}

	/**
	 * Inverse sens du deplacement vertical.
	 */
	public void inverserDy() {
		dy = -dy;
	}

	/**
	 * Inverse sens des deplacements horizontal et vertical.
	 */
	public void inverserDxEtDy() {
		dx = -dx;
		dy = -dy;
	}

	/**
	 * Fait effectuer au visage un deplacement elementaire. La position du coin
	 * superieur gauche du visage est modifiee en lui ajoutant le deplacement
	 * elementaire defini par dx et dy.
	 */
	public void deplacer() {
		xhg += dx;
		yhg += dy;
	}

	/**
	 * Fait effectuer au visage un deplacement elementaire. La position du coin
	 * superieur gauche du visage est modifiee en lui ajoutant le deplacement
	 * elementaire defini par dx et dy. Si le visage depasse de l'un des bords
	 * de la zone de dessin il inverse sa direction de deplacement.
	 */
	public void deplacerAvecRebond() {
		if (bordGaucheAtteint() || bordDroitAtteint())
			inverserDx();
		if (bordHautAtteint() || bordBasAtteint())
			inverserDy();
		xhg += dx;
		yhg += dy;
	}

	/**
	 * Evalue si le visage atteint le bord gauche de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le visage
	 *         intersecte le cote gauche de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordGaucheAtteint() {
		return (xhg < 0);
	}

	/**
	 * Evalue si le visage atteint le bord droit de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le visage
	 *         intersecte le cote droit de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordDroitAtteint() {
		return ((xhg + largeur) > d.getLargeur());
	}

	/**
	 * Evalue si le visage atteint le bord haut de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le visage
	 *         intersecte le cote haut de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordHautAtteint() {
		return (yhg < 0);
	}

	/**
	 * Evalue si le visage atteint le bord bas de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le visage
	 *         intersecte le cote bas de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordBasAtteint() {
		return ((yhg + hauteur) >= d.getHauteur());
	}

	/**
	 * Evalue si le visage atteint l'un des bords de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le visage
	 *         intersecte l'un des cotes de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordAtteint() {
		return bordDroitAtteint() || bordGaucheAtteint() || bordHautAtteint()
				|| bordBasAtteint();
	}

	/**
	 * fixe la zône de dessin dans laquelle le visage est affiche.
	 * 
	 * @param d
	 *            reference de la^zône de dessin associee au Visage
	 * 
	 * @see Dessin
	 */
	public void setDessin(Dessin d) {
		this.d = d;
	}

	/**
	 * affiche le visage.
	 * 
	 * @param g
	 *            le contexte graphique de la zône de dessin en charge de
	 *            l'affichage.
	 * 
	 * @see java.awt.Graphics
	 * @see Dessinable
	 */
	public void dessiner(Graphics g) {
		// dessiner le contour du visage
		g.drawOval(xhg, yhg, largeur, hauteur);

		// dessiner la bouche
		g.drawLine(xhg + largeur / 4, yhg + (2 * hauteur) / 3, xhg
				+ (3 * largeur) / 4, yhg + (2 * hauteur) / 3);

		// dessiner les yeux
		int largeurOeil = largeur / 5;
		int hauteurOeil = hauteur / 5;
		g.drawOval(xhg + largeurOeil, yhg + hauteurOeil, largeurOeil,
				hauteurOeil);
		g.drawOval(xhg + 3 * largeurOeil, yhg + hauteurOeil, largeurOeil,
				hauteurOeil);

	}

} // VisageRond

