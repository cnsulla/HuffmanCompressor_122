import java.io.*;
import java.util.*;

public class SerializeTree
{
    private ArrayList<Integer> pOrder = new ArrayList<Integer>(0); 
    private String filename;

    public SerializeTree(HuffmanNode node, String directory, String fn)
    {
        filename = fn;
        try { 
            File f = new File(filename);
            f.createNewFile();
        }
        catch (Exception e) {
            System.err.println(e); 
        }

        storeTree(node);
        writeToFile(pOrder, directory);
    }


    public void writeToFile(ArrayList<Integer> fileContent, String dir)//(include filename reference later)
    {
        int i = 0;

        try(FileOutputStream out = new FileOutputStream((dir+filename), true)){
            while (i < fileContent.size())
            {
                out.write( (byte) ((fileContent.get(i)) >> 24 ) & 0xff);
                out.write( (byte) ((fileContent.get(i)) >> 16 ) & 0xff);
                out.write( (byte) ((fileContent.get(i)) >> 8 ) & 0xff);
                out.write( (byte) ((fileContent.get(i)) & 0xff) );
                // System.out.println( fileContent.get(i) + " " + i);
                i++;
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void inOrder(HuffmanNode node)
    {
        if (node!=null) 
        {
            System.out.println(node.pVal);
            inOrder(node.left);
            inOrder(node.right);
        }
    }

    public void storeTree(HuffmanNode node)
    {
        
        if ( node == null)
        {
            return;
        }

        pOrder.add(node.pVal);

        storeTree( node.left);
        storeTree( node.right);
    }
}