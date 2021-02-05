package dal.asd.catme.database;

public interface IDatabaseAbstractFactory
{
    IDatabaseAccess makeDatabaseAccess();
}
