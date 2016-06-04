package View;

import Control.PaperFiller;
import Control.PaperMaker;
import Control.PaperModifier;
import Control.PaperViewer;

/**
 * Created by Qizixi on 6/3/2016.
 */
public class ControlPanel {
    public PaperFiller filler;
    public PaperMaker maker;
    public PaperModifier modifier;
    public PaperViewer viewer;

    public ControlPanel(PaperFiller filler,PaperMaker maker,PaperModifier modifier,PaperViewer viewer){
        this.filler = filler;
        this.maker = maker;
        this.modifier = modifier;
        this.viewer = viewer;
    }

    public ControlPanel(){
        this.filler = new PaperFiller();
        this.maker = new PaperMaker();
        this.modifier = new PaperModifier();
        this.viewer = new PaperViewer();
    }
}
